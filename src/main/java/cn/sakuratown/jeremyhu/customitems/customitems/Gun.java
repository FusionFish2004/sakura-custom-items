package cn.sakuratown.jeremyhu.customitems.customitems;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import cn.sakuratown.jeremyhu.customitems.enchantments.Enchantment;
import cn.sakuratown.jeremyhu.customitems.enchantments.GunEnchantment;
import cn.sakuratown.jeremyhu.customitems.items.Item;
import cn.sakuratown.jeremyhu.customitems.utils.ActionBarUtil;
import cn.sakuratown.jeremyhu.customitems.weapon.Bullet;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * 枪械类,继承物品类
 * @author JeremyHu
 */

public class Gun extends Item {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);

    private double health = 100;
    private double speed = 1;
    private static final int maxClip = 35;

    private int clip;

    private static final NamespacedKey CLIP = new NamespacedKey(plugin,"clip");

    public int getClip() {
        return clip;
    }

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

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        if(!container.has(CLIP,PersistentDataType.INTEGER)){
            container.set(CLIP,PersistentDataType.INTEGER,maxClip);
        }

        clip = container.get(CLIP, PersistentDataType.INTEGER);

        if(clip == 0) {
            this.reload(player);
            return;
        }

        this.shoot(player);
        this.cooldown(player);

        container.set(CLIP,PersistentDataType.INTEGER,clip);
        itemStack.setItemMeta(itemMeta);

    }

    public void shoot(Player player){
        clip --;
        ActionBarUtil.sendActionBar(player, "§f§l" + clip + " | " + maxClip);
        Vector direction = player.getLocation().getDirection();
        Location start = player.getEyeLocation();

        List<Enchantment> enchantments = getEnchantments();
        if(enchantments.isEmpty()) {
            //没有附魔
            Bullet bullet = new Bullet(start, direction, health, speed, player, getDamage());
            bullet.start();
        }else{
            List<Bullet> bullets = new ArrayList<>();
            bullets.add(new Bullet(start, direction, health, speed, player, getDamage()));
            enchantments.stream()
                    .filter(enchantment -> "GunEnchantment".equalsIgnoreCase(enchantment.getCatagory()))
                    .map(GunEnchantment::toGunEnchantment)
                    .forEach(gunEnchantment -> gunEnchantment.trigger(player,bullets));
        }
    }

    public void reload(Player player){
        ActionBarUtil.sendActionBar(player,"§a§l换弹中...");
        player.setCooldown(player.getInventory().getItemInMainHand().getType(), 20);

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(CLIP,PersistentDataType.INTEGER,maxClip);
        itemStack.setItemMeta(itemMeta);
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
