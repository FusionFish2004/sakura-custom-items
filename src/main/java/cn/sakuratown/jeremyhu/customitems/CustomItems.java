package cn.sakuratown.jeremyhu.customitems;

import cn.sakuratown.jeremyhu.customitems.items.Item;
import cn.sakuratown.jeremyhu.customitems.listeners.PlayerInteractListener;
import cn.sakuratown.jeremyhu.customitems.utils.FileUtil;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * 插件主类
 * @author JeremyHu
 */

public class CustomItems extends JavaPlugin {

    public final FileUtil FILE_UTIL = new FileUtil(this);
    //定义文件读取实用类
    private HashMap<UUID, Item> itemHashMap = new HashMap<>();
    //定义存储UUID和物品的hashmap

    @Override
    public void onEnable(){
        initItemMap();
        //初始化物品hashmap
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
            // 如果我们已经判断好sender是一名玩家之后,我们就可以将其强转为Player对象,把它作为一个"玩家"来处理
            Player player = (Player) sender;

            return true;
        }
        return false;
    }

    public HashMap<UUID, Item> getItemHashMap() {
        return itemHashMap;
    }

    public void initItemMap(){
        List<File> files = FILE_UTIL.getFiles(new File(getDataFolder(),"items"));
        //获取items目录下所有文件
        if(files == null || files.isEmpty()) return;
        //如果为空则返回
        files.forEach(file -> {
            Item item = new Gson().fromJson(FILE_UTIL.getJson(file),Item.class);
            itemHashMap.merge(item.getUuid(), item, (oldItem, newItem) -> newItem);
        });
        //遍历，重新映射hashmap
    }
}
