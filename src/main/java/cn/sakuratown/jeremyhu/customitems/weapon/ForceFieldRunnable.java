package cn.sakuratown.jeremyhu.customitems.weapon;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.stream.Collectors;

public class ForceFieldRunnable extends BukkitRunnable {

    private Player player;
    private boolean powered = false;

    public ForceFieldRunnable(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isPowered() {
        return powered;
    }

    public void setPowered(boolean powered) {
        this.powered = powered;
    }

    @Override
    public void run() {
        List<Projectile> projectiles = player.getNearbyEntities(5,5,5)
                .stream()
                .filter(entity -> entity instanceof Projectile)
                .map(entity -> (Projectile)entity)
                .filter(projectile -> !projectile.getShooter().equals(player))
                .filter(projectile -> !projectile.isOnGround())
                .collect(Collectors.toList());
        projectiles.forEach(projectile -> {
            World world = projectile.getWorld();
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.LIME,1);
            world.spawnParticle(Particle.REDSTONE,projectile.getLocation(), 5,0.2,0.2,0.2,0.00001,dustOptions);

            Vector velocity = projectile.getVelocity().clone();
            Vector vector = new Vector(0,1,0).normalize().multiply(1).add(Vector.getRandom().setY(0).normalize());

            velocity.add(vector);
            projectile.setVelocity(velocity);

        });

    }
}
