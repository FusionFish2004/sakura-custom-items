package cn.sakuratown.jeremyhu.customitems.utils;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class ItemUtil {

    private static final NamespacedKey UUID_KEY = new NamespacedKey(CustomItems.plugin,"uuid");

    public static boolean isItem(ItemMeta itemMeta){
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        return persistentDataContainer.has(UUID_KEY, PersistentDataType.STRING);
    }

    public static UUID getItemUUID(ItemMeta itemMeta){
        return UUID.fromString(itemMeta.getPersistentDataContainer().get(UUID_KEY, PersistentDataType.STRING));
    }
}
