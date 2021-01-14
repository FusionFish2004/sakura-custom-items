package cn.sakuratown.jeremyhu.customitems.weapon;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TracingBullet extends Bullet{
    public TracingBullet(Location start, Vector direction, double health, double speed, Player shooter, double damage) {
        super(start, direction, health, speed, shooter, damage);
    }

    @Override
    public void run(){
        checkCollision();
        runBasic();

        drawParticle();
    }
}
