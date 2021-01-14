package cn.sakuratown.jeremyhu.customitems.customitems;

import cn.sakuratown.jeremyhu.customitems.items.Item;
import cn.sakuratown.jeremyhu.customitems.weapon.Bullet;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class Gun extends Item {

    private double health = 100;
    private double speed = 1;

    public double getHealth() {
        return health;
    }

    public void setRange(double range) {
        this.health = health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void trigger(PlayerInteractEvent event){
        Player player = event.getPlayer();
        this.shoot(player);
        this.cooldown(player);
    }

    public void shoot(Player player){
        Vector direction = player.getLocation().getDirection();
        Location start = player.getEyeLocation();
        Bullet bullet = new Bullet(start,direction,health,speed, player);
        bullet.start();
    }

    public static Gun fromItem(Item item){
        Gun gun = new Gun();
        gun.setDamage(item.getDamage());
        gun.setType(item.getType());
        gun.setEnchantments(item.getEnchantments());
        gun.setName(item.getName());
        gun.setCD(item.getCD());
        return gun;
    }

}
