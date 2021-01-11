package cn.sakuratown.jeremyhu.customitems.items;


import cn.sakuratown.jeremyhu.customitems.CustomItems;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

/**
 * 工厂模式
 * @author JeremyHu
 */

public class ItemFactory {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);

    public static Item getItem(UUID uuid){
        return null;
    }
}
