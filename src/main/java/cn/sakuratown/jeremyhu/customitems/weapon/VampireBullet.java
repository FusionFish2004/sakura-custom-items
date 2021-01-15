package cn.sakuratown.jeremyhu.customitems.weapon;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VampireBullet extends Bullet{
    public VampireBullet(Location start, Vector direction, double health, double speed, Player shooter, double damage) {
        super(start, direction, health, speed, shooter, damage);
        super.setVampire(true);
    }

    public static VampireBullet fromBullet(Bullet bullet){

        Location start = bullet.getPosition();
        Vector direction = bullet.getDirection();
        double health = bullet.getHealth();
        double speed = bullet.getSpeed();
        double damage = bullet.getDamage();
        Player shooter = bullet.getShooter();

        return new VampireBullet(start,direction,health,speed,shooter,damage);
    }
}
