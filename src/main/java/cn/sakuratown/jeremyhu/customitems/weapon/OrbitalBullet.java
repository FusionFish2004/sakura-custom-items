package cn.sakuratown.jeremyhu.customitems.weapon;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class OrbitalBullet extends Bullet{

    private int angle = 0;
    private static final double radius = 1.5;
    private Vector vector;

    public OrbitalBullet(Location start, Vector direction, double health, double speed, Player shooter, double damage) {
        super(start, direction, health, speed, shooter, damage);
        vector = direction.clone().multiply(radius);
        setPosition(getPosition().clone().add(vector));
    }

    @Override
    public void run(){
        vector.rotateAroundY(Math.toRadians(6));
        checkCollision();
        runBasic();
        Location location = getShooter().getEyeLocation();
        setPosition(location.clone().add(vector));


        drawParticle();
    }

    public static OrbitalBullet fromBullet(Bullet bullet){

        Location start = bullet.getPosition();
        Vector direction = bullet.getDirection();
        double health = bullet.getHealth();
        double speed = bullet.getSpeed();
        double damage = bullet.getDamage();
        Player shooter = bullet.getShooter();

        OrbitalBullet orbitalBullet = new OrbitalBullet(start,direction,health,speed,shooter,damage);
        orbitalBullet.setFlame(bullet.isFlame());
        orbitalBullet.setVampire(bullet.isVampire());

        return orbitalBullet;
    }
}
