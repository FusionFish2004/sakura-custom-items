package cn.sakuratown.jeremyhu.customitems.listeners;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * 玩家交互监听器
 * @author JeremyHu
 */

public class PlayerInteractListener implements Listener {

    private CustomItems plugin;

    public PlayerInteractListener(CustomItems plugin){
        this.plugin = plugin;
        plugin.getLogger().info(this.getClass().getName() + "registered.");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){

    }
}
