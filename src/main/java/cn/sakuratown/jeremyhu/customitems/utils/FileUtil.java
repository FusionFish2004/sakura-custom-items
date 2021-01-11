package cn.sakuratown.jeremyhu.customitems.utils;

import cn.sakuratown.jeremyhu.customitems.CustomItems;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件实用类
 * @author JeremyHu
 */

public class FileUtil {

    private CustomItems plugin;

    public FileUtil(CustomItems plugin){
        this.plugin = plugin;
    }

    public List<File> getFiles(File dir){

        if(!dir.isDirectory() || !dir.exists()){
            return null;
        }

        List<File> files = new ArrayList<>();
        for(int i = 0; i < dir.listFiles().length; i++){
            files.add(dir.listFiles()[i]);
        }
        return files;
    }
}
