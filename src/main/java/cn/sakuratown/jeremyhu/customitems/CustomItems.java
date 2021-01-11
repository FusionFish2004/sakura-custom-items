package cn.sakuratown.jeremyhu.customitems;

import cn.sakuratown.jeremyhu.customitems.items.Item;
import cn.sakuratown.jeremyhu.customitems.items.ItemFactory;
import cn.sakuratown.jeremyhu.customitems.listeners.PlayerInteractListener;
import cn.sakuratown.jeremyhu.customitems.utils.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

/**
 * 插件主类
 * @author JeremyHu
 */

public class CustomItems extends JavaPlugin {

    public static final ItemFactory FACTORY = new ItemFactory();
    //定义物品工厂类
    public final FileUtil FILE_UTIL = new FileUtil(this);
    //定义文件读取实用类
    private HashMap<UUID, Item> itemHashMap = new HashMap<>();
    //定义存储UUID和物品的hashmap

    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this),this);
        //注册玩家交互监听器
    }

    public static Plugin getInstance(){
        return Bukkit.getPluginManager().getPlugin("SakuraCustomItems");
    }

    public HashMap<UUID, Item> getItemHashMap() {
        return itemHashMap;
    }

}
