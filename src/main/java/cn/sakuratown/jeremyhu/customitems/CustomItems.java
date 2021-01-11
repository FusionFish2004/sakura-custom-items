package cn.sakuratown.jeremyhu.customitems;

import cn.sakuratown.jeremyhu.customitems.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 插件主类
 * @author JeremyHu
 */

public class CustomItems extends JavaPlugin {
    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this),this);
        //注册玩家交互监听器
    }
}
