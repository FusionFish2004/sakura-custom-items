package cn.sakuratown.jeremyhu.customitems.items;

import cn.sakuratown.jeremyhu.customitems.utils.ItemUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Date;
import java.util.UUID;

/**
 * 基础物品类
 * @author JeremyHu
 */

public class Item {

    private String name;
    private String type;
    private String uuid;
    private long CD;

    public Item(UUID uuid){
        this.uuid = uuid.toString();
    }

    public Item(){
        this.uuid = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return UUID.fromString(uuid);
    }

    public void trigger(Player player){
        if(!checkCD(player)) return;
    }

    private boolean checkCD(Player player){
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        long time = new Date().getTime();
        long lastUsage = ItemUtil.getItemLastUsage(itemMeta);
        if(lastUsage != 0L && (time - lastUsage) < this.CD) return false;
        //判断物品是否等待完成
        return true;
    }
}
