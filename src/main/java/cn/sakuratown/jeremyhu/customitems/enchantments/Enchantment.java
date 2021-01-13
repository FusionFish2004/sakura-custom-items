package cn.sakuratown.jeremyhu.customitems.enchantments;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Enchantment {
    private String type;
    private int lvl;

    public int getLvl() {
        return lvl;
    }

    public String getType() {
        return type;
    }

    public void trigger(Player user, Entity entity){

    }
}
