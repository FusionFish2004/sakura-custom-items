package cn.sakuratown.jeremyhu.customitems.items;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ItemBuilder {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);
    private static final NamespacedKey NAME = new NamespacedKey(plugin,"name");
    private static final NamespacedKey COOL_DOWN = new NamespacedKey(plugin,"cool_down");
    private static final NamespacedKey DAMAGE = new NamespacedKey(plugin,"damage");
    private static final NamespacedKey ENCHANTMENTS = new NamespacedKey(plugin,"enchantments");
    private static final NamespacedKey TYPE = new NamespacedKey(plugin,"type");

    private List<Item.Enchantment> enchantments;
    private String name;
    private int damage;
    private int cd;
    private String type;

    public ItemBuilder enchantments(List<Item.Enchantment> enchantments){
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder name(String name){
        this.name = name;
        return this;
    }

    public ItemBuilder damage(int damage){
        this.damage = damage;
        return this;
    }

    public ItemBuilder cd(int cd){
        this.cd = cd;
        return this;
    }

    public ItemBuilder type(String type){
        this.type = type;
        return this;
    }

    public ItemStack build(){
        ItemStack itemStack = new ItemStack(Material.PRISMARINE_SHARD);
        if(damage == 0 || cd == 0 || name == "") return itemStack;
        //若没有存储数据，则返回普通物品
        ItemMeta itemMeta = ItemUtil.createMeta(itemStack.getItemMeta());
        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }
}
