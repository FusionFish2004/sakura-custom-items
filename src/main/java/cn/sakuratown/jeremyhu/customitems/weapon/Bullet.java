package cn.sakuratown.jeremyhu.customitems.weapon;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class Bullet extends BukkitRunnable {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);
    private int i = 0;
    private Vector direction;
    private double health;
    private double speed;
    private Location position;
    private World world;

    public Bullet(Location start, Vector direction, double health, double speed){
        this.speed = speed;
        this.direction = direction.clone();
        this.position = start.clone();
        this.health = health;
        this.world = position.getWorld();
    }

    @Override
    public void run() {


        if(i >= health){
            this.cancel();
        }
        i++;

        position.add(direction.clone().multiply(speed));

        world.spawnParticle(Particle.END_ROD, position, 3, 0, 0, 0, 0.1);

    }

    public void start(){
        this.runTaskTimerAsynchronously(plugin,0L,1L);
    }
}
