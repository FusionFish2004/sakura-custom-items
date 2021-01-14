package cn.sakuratown.jeremyhu.customitems.listeners;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import cn.sakuratown.jeremyhu.customitems.items.ItemBuilder;
import cn.sakuratown.jeremyhu.customitems.items.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * 玩家交互监听器
 * @author JeremyHu
 */

public class PlayerInteractListener implements Listener {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);

    public PlayerInteractListener(){
        plugin.getLogger().info(this.getClass().getTypeName() + " registered.");
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

        if(player.hasCooldown(itemStack.getType())){
            event.setCancelled(true);
            return;
        }


        ItemBuilder.buildItem(itemMeta).trigger(event);
        event.setCancelled(true);
    }

}
