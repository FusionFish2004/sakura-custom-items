package cn.sakuratown.jeremyhu.customitems;

import cn.sakuratown.jeremyhu.customitems.items.ItemBuilder;
import cn.sakuratown.jeremyhu.customitems.listeners.EntityDamageByEntityListener;
import cn.sakuratown.jeremyhu.customitems.listeners.PlayerInteractListener;
import cn.sakuratown.jeremyhu.customitems.utils.FileUtil;
import cn.sakuratown.jeremyhu.customitems.weapon.ForceFieldRunnable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * 插件主类
 * @author JeremyHu
 */

public class CustomItems extends JavaPlugin {

    public final FileUtil FILE_UTIL = new FileUtil(this);
    //定义文件读取实用类
    private HashMap<Player, ForceFieldRunnable> fieldRunnableHashMap = new HashMap<>();

    public HashMap<Player, ForceFieldRunnable> getFieldRunnableHashMap() {
        return fieldRunnableHashMap;
    }

    public void setFieldRunnableHashMap(HashMap<Player, ForceFieldRunnable> fieldRunnableHashMap) {
        this.fieldRunnableHashMap = fieldRunnableHashMap;
    }

    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(),this);
        //注册玩家交互监听器
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(),this);
        //注册实体伤害监听器
        Bukkit.getPluginCommand("si").setExecutor(this);
        //注册测试指令
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("si")) {
            if (!(sender instanceof Player)) {
                return true;
            }
            //命令发送者不属于玩家，返回
            Player player = (Player) sender;
            if(!player.isOp()) return false;
            player.getInventory().addItem(ItemBuilder.getInstance()
                    .cd(10)
                    .type("ForceField")
                    .name("小e的胸罩")
                    .damage(0)
                    .build());
            player.sendMessage("已给予你一个特殊物品");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
