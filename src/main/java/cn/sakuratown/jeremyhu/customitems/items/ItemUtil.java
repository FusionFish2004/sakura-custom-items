package cn.sakuratown.jeremyhu.customitems.items;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import cn.sakuratown.jeremyhu.customitems.enchantments.Enchantment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
        lore.add(persistentDataContainer.get(TYPE, PersistentDataType.STRING));

        String jsonString = persistentDataContainer.get(ENCHANTMENTS, PersistentDataType.STRING);
        if (!"{[]}".equals(jsonString)) {
            lore.add("§f-------------");
            List<Enchantment> enchantments = new Gson().fromJson(jsonString, new TypeToken<List<Enchantment>>() {}.getType());
            enchantments.forEach(enchantment -> {
                String type = enchantment.getType();
                String lvl = getLvl(enchantment.getLvl());
                lore.add("§1" + type + lvl);
            });
        }
        lore.add("§f-------------");
        lore.add("§f基础伤害：§7§l" + String.valueOf(persistentDataContainer.get(DAMAGE, PersistentDataType.INTEGER)));
        lore.add("§f冷却时间：§7§l" + String.valueOf(persistentDataContainer.get(COOL_DOWN, PersistentDataType.INTEGER)));
        itemMeta.setLore(lore);
        return itemMeta;
    }

    public static String getLvl(int lvl){
        //罗马数字转换
        switch (lvl){
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
        }
        return "";
    }
}
