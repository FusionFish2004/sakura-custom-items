package cn.sakuratown.jeremyhu.customitems.items;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 工厂模式类
 * @author JeremyHu
 */

public class ItemFactory {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);
    private static final NamespacedKey COOL_DOWN = new NamespacedKey(plugin,"cool_down");
    private static final NamespacedKey ENCHANTMENTS = new NamespacedKey(plugin,"enchantments");
    private static final NamespacedKey NAME = new NamespacedKey(plugin,"name");
    private static final Set<NamespacedKey> keySet = new HashSet<>(Arrays.asList(COOL_DOWN,ENCHANTMENTS,NAME));


    public static Item getItem(ItemMeta itemMeta){
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        //获取容器
        if(!ItemUtil.isItem(itemMeta)) return null;
        //如果不含有数据则返回null
        Item item = new Item();
        Meta meta = new Meta();
        container.getKeys().stream()
                .filter(keySet::contains)
                .forEach(namespacedKey -> {
                    switch(namespacedKey.getKey()){
                        case "name":
                            meta.setName(container.get(NAME, PersistentDataType.STRING));
                        case "cool_down":
                            meta.setCD(container.get(COOL_DOWN, PersistentDataType.INTEGER));
                        case "enchantments":
                            String jsonString = container.get(ENCHANTMENTS, PersistentDataType.STRING);
                            List<Item.Enchantment> enchantments = new Gson().fromJson(jsonString,new TypeToken<List<Item.Enchantment>>(){}.getType());
                            meta.setEnchantments(enchantments);
                    }
                });
        item.setMeta(meta);
        return item;
    }
}
