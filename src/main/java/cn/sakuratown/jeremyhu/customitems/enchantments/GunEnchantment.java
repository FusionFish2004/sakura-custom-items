package cn.sakuratown.jeremyhu.customitems.enchantments;

import cn.sakuratown.jeremyhu.customitems.weapon.Bullet;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * 枪械附魔基础类
 * @author JeremyHu
 */

public class GunEnchantment extends Enchantment{

    public GunEnchantment(){
        setCatagory(this.getClass().getSimpleName());
    }

    @Override
    public boolean isGunEnchantment(){
        return true;
    }

    public List<Bullet> trigger(Player player, List<Bullet> bullets){
        return bullets;
    }
}
