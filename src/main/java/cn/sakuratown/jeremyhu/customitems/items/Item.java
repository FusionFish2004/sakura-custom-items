package cn.sakuratown.jeremyhu.customitems.items;

import cn.sakuratown.jeremyhu.customitems.enchantments.Enchantment;
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
    private int damage;
    private String type;

    public Item(){

    }

    public Item(String name,int CD,List<Enchantment> enchantments,int damage,String type){
        this.name = name;
        this.CD = CD;
        this.enchantments = enchantments;
        this.type = type;
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnchantments(List<Enchantment> enchantments) {
        this.enchantments = enchantments;
    }

    public void setCD(int CD) {
        this.CD = CD;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCD() {
        return CD;
    }

    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

    public void trigger(PlayerInteractEvent event){

    }

    public void cooldown(Player player){
        player.setCooldown(player.getInventory().getItemInMainHand().getType(),CD);
    }

}
