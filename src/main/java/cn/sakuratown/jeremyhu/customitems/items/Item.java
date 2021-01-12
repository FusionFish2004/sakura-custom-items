package cn.sakuratown.jeremyhu.customitems.items;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

/**
 * 基础物品类
 * @author JeremyHu
 */

public class Item {

    private String name;
    private int CD;
    private List<Enchantment> enchantments;

    public String getName() {
        return name;
    }

    public int getCD() {
        return CD;
    }

    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

    public void trigger(PlayerInteractEvent event){
        Player player = event.getPlayer();
        player.setCooldown(player.getInventory().getItemInMainHand().getType(),CD);
    }

    class Enchantment {
        private String type;
        private int lvl;
    }

    public void setMeta(Meta meta){
        this.name = meta.getName();
        this.CD = meta.getCD();
        this.enchantments = meta.getEnchantments();
    }
}
