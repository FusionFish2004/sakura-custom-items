package cn.sakuratown.jeremyhu.customitems.items;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import com.google.gson.Gson;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
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
        ItemStack itemStack = new ItemStack(Material.LEATHER_HORSE_ARMOR);
        if(damage == 0 || cd == 0 || name == "") return itemStack;
        //若没有存储数据，则返回普通物品
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(DAMAGE, PersistentDataType.INTEGER, this.damage);
        container.set(NAME, PersistentDataType.STRING, this.name);
        container.set(COOL_DOWN, PersistentDataType.INTEGER, this.cd);
        container.set(TYPE, PersistentDataType.STRING, this.type);

        if(enchantments == null){
            container.set(ENCHANTMENTS,PersistentDataType.STRING,"{[]}");
        }else{
            container.set(ENCHANTMENTS,PersistentDataType.STRING,new Gson().toJson(enchantments));
        }

        itemMeta = ItemUtil.createMeta(itemMeta);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemBuilder getInstance(){
        //获取实例
        return new ItemBuilder();
    }
}
