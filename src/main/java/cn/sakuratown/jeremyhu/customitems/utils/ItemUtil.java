package cn.sakuratown.jeremyhu.customitems.utils;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

/**
 * 物品实用类
 * @author JeremyHu
 */

public class ItemUtil {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);

    private static final NamespacedKey UUID_KEY = new NamespacedKey(plugin,"uuid");
    private static final NamespacedKey LAST_USAGE = new NamespacedKey(plugin,"last_usage");

    public static boolean isItem(ItemMeta itemMeta){
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        return persistentDataContainer.has(UUID_KEY, PersistentDataType.STRING);
    }

    public static UUID getItemUUID(ItemMeta itemMeta){
        return UUID.fromString(itemMeta.getPersistentDataContainer().get(UUID_KEY, PersistentDataType.STRING));
    }

    public static long getItemLastUsage(ItemMeta itemMeta){
        if(!itemMeta.getPersistentDataContainer().has(LAST_USAGE, PersistentDataType.LONG)){
            return 0;
        }
        return itemMeta.getPersistentDataContainer().get(LAST_USAGE, PersistentDataType.LONG);
    }
}
