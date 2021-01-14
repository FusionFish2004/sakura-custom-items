package cn.sakuratown.jeremyhu.customitems.items;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import cn.sakuratown.jeremyhu.customitems.enchantments.Enchantment;
import cn.sakuratown.jeremyhu.customitems.enchantments.EnchantmentsBuilder;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * 物品实用类
 * @author JeremyHu
 */

public class ItemUtil {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);

    private static final NamespacedKey NAME = new NamespacedKey(plugin,"name");
    private static final NamespacedKey TYPE = new NamespacedKey(plugin,"type");
    private static final NamespacedKey COOL_DOWN = new NamespacedKey(plugin,"cool_down");
    private static final NamespacedKey DAMAGE = new NamespacedKey(plugin,"damage");
    private static final NamespacedKey ENCHANTMENTS = new NamespacedKey(plugin,"enchantments");

    public static boolean isItem(ItemMeta itemMeta){
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        return persistentDataContainer.has(DAMAGE, PersistentDataType.INTEGER);
    }


    public static ItemMeta createMeta(ItemMeta itemMeta) {
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        itemMeta.setDisplayName("§f" + persistentDataContainer.get(NAME, PersistentDataType.STRING));
        List<String> lore = new ArrayList<>();

        String jsonString = persistentDataContainer.get(ENCHANTMENTS, PersistentDataType.STRING);
        if (!"{[]}".equals(jsonString)) {
            lore.add("§f-------------");
            List<Enchantment> enchantments = EnchantmentsBuilder.fromJson(jsonString).build();
            enchantments.forEach(enchantment -> lore.add("§9" + enchantment.toString()));
        }
        lore.add("§f-------------");
        lore.add("§f基础伤害：§7§l" + String.valueOf(persistentDataContainer.get(DAMAGE, PersistentDataType.INTEGER)));
        lore.add("§f冷却时间：§7§l" + String.valueOf(persistentDataContainer.get(COOL_DOWN, PersistentDataType.INTEGER)));
        itemMeta.setLore(lore);
        return itemMeta;
    }
}
