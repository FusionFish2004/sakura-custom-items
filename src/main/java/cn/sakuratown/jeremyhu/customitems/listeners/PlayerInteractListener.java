package cn.sakuratown.jeremyhu.customitems.listeners;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import cn.sakuratown.jeremyhu.customitems.utils.ItemUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;


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
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        //获取物品meta
        if(!ItemUtil.isItem(itemMeta)) return;
        //检测物品meta是否拥有uuid数据，若否则返回
        UUID uuid = ItemUtil.getItemUUID(itemMeta);
        //获取UUID
        if(plugin.getItemHashMap().keySet().contains(uuid)){
            plugin.getItemHashMap().get(uuid).trigger(event);
            //触发物品事件
        }else throw new RuntimeException();
    }
}
