package cn.sakuratown.jeremyhu.customitems.listeners;


import cn.sakuratown.jeremyhu.customitems.CustomItems;
import cn.sakuratown.jeremyhu.customitems.items.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 实体伤害监听器
 * @author JeremyHu
 */

public class EntityDamageByEntityListener implements Listener {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);

    public EntityDamageByEntityListener(){
        plugin.getLogger().info(this.getClass().getSimpleName() + " registered.");
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){

        if(!(event.getDamager() instanceof Player)) return;

        Player player = (Player)event.getDamager();
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(itemStack.getType() == Material.AIR) return;
        ItemMeta itemMeta = itemStack.getItemMeta();
        //获取物品meta
        if(!ItemUtil.isItem(itemMeta)) return;
        //检测物品meta是否拥有数据，若否则返回

    }
}
