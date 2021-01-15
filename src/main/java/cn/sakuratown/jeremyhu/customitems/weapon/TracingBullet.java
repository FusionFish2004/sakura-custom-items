package cn.sakuratown.jeremyhu.customitems.weapon;

import cn.sakuratown.jeremyhu.customitems.utils.ActionBarUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TracingBullet extends Bullet{

    private World world;
    private LivingEntity tracking;

    public TracingBullet(Location start, Vector direction, double health, double speed, Player shooter, double damage) {
        super(start, direction, health, speed, shooter, damage);
    }

    @Override
    public void run(){
        checkCollision();
        runBasic();
        world = getPosition().getWorld();
        if(tracking == null){
            Collection<Entity> entities = world.getNearbyEntities(getPosition(),5,5,5,
                    entity -> !entity.equals(getShooter()) && entity instanceof LivingEntity);
            tracking = getNearestEntity(entities);
            if(tracking!=null){
                ActionBarUtil.sendActionBar(getShooter(),"§6§l锁定实体：" + tracking.getType().name());
            }
        }
        if(tracking!=null){
            Vector attract = tracking.getEyeLocation().toVector().clone().subtract(getPosition().toVector()).normalize().multiply(0.1);
            setDirection(getDirection().clone().add(attract).normalize());
        }

        setPosition(getPosition().add(getDirection().clone().multiply(getSpeed())));
        drawParticle();
    }

    public static TracingBullet fromBullet(Bullet bullet){

        Location start = bullet.getPosition();
        Vector direction = bullet.getDirection();
        double health = bullet.getHealth();
        double speed = bullet.getSpeed();
        double damage = bullet.getDamage();
        Player shooter = bullet.getShooter();

        TracingBullet tracingBullet = new TracingBullet(start,direction,health,speed,shooter,damage);
        tracingBullet.setFlame(bullet.isFlame());
        tracingBullet.setVampire(bullet.isVampire());

        return tracingBullet;
    }

    public LivingEntity getNearestEntity(Collection<Entity> entities){
        List<LivingEntity> livingEntities = entities
                .stream()
                .map(entity -> (LivingEntity) entity)
                .sorted(Comparator.comparing(livingEntity -> livingEntity.getEyeLocation().distance(getPosition()))).collect(Collectors.toList());
        return livingEntities.stream().findFirst().orElse(null);
    }
}
