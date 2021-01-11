package cn.sakuratown.jeremyhu.customitems.items;


import cn.sakuratown.jeremyhu.customitems.CustomItems;

import java.util.UUID;

/**
 * 工厂模式
 * @author JeremyHu
 */

public class ItemFactory {

    private CustomItems plugin;

    public ItemFactory(CustomItems plugin){
        this.plugin = plugin;
    }

    public static Item getItem(UUID uuid){
        return null;
    }
}
