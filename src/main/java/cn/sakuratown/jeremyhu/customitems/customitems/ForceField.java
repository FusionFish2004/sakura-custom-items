package cn.sakuratown.jeremyhu.customitems.customitems;

import cn.sakuratown.jeremyhu.customitems.CustomItems;
import cn.sakuratown.jeremyhu.customitems.items.Item;
import cn.sakuratown.jeremyhu.customitems.utils.ActionBarUtil;
import cn.sakuratown.jeremyhu.customitems.weapon.ForceFieldRunnable;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class ForceField extends Item {

    private static CustomItems plugin = JavaPlugin.getPlugin(CustomItems.class);
    private Player player;

    @Override
    public void trigger(PlayerInteractEvent event){
        this.player = event.getPlayer();
        HashMap<Player, ForceFieldRunnable> fieldRunnableHashMap = plugin.getFieldRunnableHashMap();
        if(fieldRunnableHashMap.get(player) == null) fieldRunnableHashMap.put(player,new ForceFieldRunnable(player));
        ForceFieldRunnable runnable = fieldRunnableHashMap.get(player);
        if(runnable.isPowered()){
            runnable.setPowered(false);
            runnable.cancel();
            fieldRunnableHashMap.put(player,null);
            ActionBarUtil.sendActionBar(player,"力场关闭");
            return;
        }else{
            runnable.runTaskTimer(plugin,0L,1L);
            runnable.setPowered(true);
            ActionBarUtil.sendActionBar(player,"力场开启");
            return;
        }
    }

    public static ForceField fromItem(Item item){
        ForceField forceField = new ForceField();
        forceField.setDamage(item.getDamage());
        forceField.setType(item.getType());
        forceField.setEnchantments(item.getEnchantments());
        forceField.setName(item.getName());
        forceField.setCD(item.getCD());
        return forceField;
    }
}
