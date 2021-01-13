package cn.sakuratown.jeremyhu.customitems.listeners;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import cn.sakuratown.jeremyhu.customitems.items.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


/**
 * 玩家交互监听器
 * @author JeremyHu
 */

public class PlayerInteractListener implements Listener {

    private CustomItems plugin;

    public PlayerInteractListener(CustomItems plugin){
        this.plugin = plugin;
        plugin.getLogger().info(this.getClass().getName() + " registered.");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(itemStack.getType() == Material.AIR) return;
        //如果是空手触发事件，返回
        ItemMeta itemMeta = itemStack.getItemMeta();
        //获取物品meta
        if(!ItemUtil.isItem(itemMeta)) return;
        //检测物品meta是否拥有数据，若否则返回

    }
}
