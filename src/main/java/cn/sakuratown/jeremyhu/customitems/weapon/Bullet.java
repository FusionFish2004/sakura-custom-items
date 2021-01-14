package cn.sakuratown.jeremyhu.customitems.weapon;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collection;

/**
 * 子弹类
 * @author JeremyHu
 */

public class Bullet extends BukkitRunnable {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);
    private int i = 0;
    private Vector direction;
    private double health;
    private double speed;
    private Location position;
    private World world;
    private Player shooter;
    private double damage;

    public Bullet(Location start, Vector direction, double health, double speed, Player shooter, double damage){
        this.speed = speed;
        this.direction = direction.clone();
        this.position = start.clone();
        this.health = health;
        this.world = position.getWorld();
        this.shooter = shooter;
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDamage() {
        return damage;
    }

    public Vector getDirection() {
        return direction;
    }

    public Player getShooter() {
        return shooter;
    }

    public Location getPosition() {
        return position;
    }

    @Override
    public void run() {

        checkCollision();
        runBasic();
        position.add(direction.clone().multiply(speed));
        drawParticle();
    }

    public void runBasic(){
        if(i >= health){
            this.cancel();
        }
        i++;
    }

    public void drawParticle(){
        world.spawnParticle(Particle.BUBBLE_POP, position, 1, 0, 0, 0, 0.1);
        //绘制粒子
    }

    public void checkCollision() {
        Collection<Entity> entityCollection = world.getNearbyEntities(position, 0.5, 0.5, 0.5, entity -> !entity.equals(shooter));
        if (!entityCollection.isEmpty()) {
            this.collide();
            entityCollection.forEach(entity -> {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.damage(damage,shooter);
                } else {
                    entity.remove();
                }
            });
            return;
            //返回，避免触发检测方块碰撞
        }
        //检测实体碰撞

        if (!world.getBlockAt(position).isPassable()) {
            collide();
            return;
        }
        //检测方块碰撞
    }

    public void collide(){
        //碰撞时触发本方法
        this.cancel();
    }

    public void start(){
        this.runTaskTimer(plugin,0L,1L);
    }
}
