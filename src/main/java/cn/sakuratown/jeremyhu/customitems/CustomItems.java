package cn.sakuratown.jeremyhu.customitems;

import cn.sakuratown.jeremyhu.customitems.listeners.PlayerInteractListener;
import cn.sakuratown.jeremyhu.customitems.utils.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 插件主类
 * @author JeremyHu
 */

public class CustomItems extends JavaPlugin {

    public final FileUtil FILE_UTIL = new FileUtil(this);
    //定义文件读取实用类

    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this),this);
        //注册玩家交互监听器
        Bukkit.getPluginCommand("si").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("si")) {
            if (!(sender instanceof Player)) {
                return true;
            }
            //命令发送者不属于玩家，返回
            Player player = (Player) sender;

            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
