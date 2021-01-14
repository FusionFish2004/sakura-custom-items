package cn.sakuratown.jeremyhu.customitems.enchantments;

import cn.sakuratown.jeremyhu.customitems.weapon.Bullet;
import cn.sakuratown.jeremyhu.customitems.weapon.TracingBullet;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 子弹追踪附魔
 * @author JeremyHu
 */

public class TrackingEnchantment extends GunEnchantment{

    public TrackingEnchantment(){
        setLvl(0);
        setName("子弹追踪");
        setCatagory(GunEnchantment.class.getSimpleName());
        setType(this.getClass().getSimpleName());
    }

    @Override
    public List<Bullet> trigger(Player player, List<Bullet> bullets){
        if(bullets.isEmpty()) return bullets;

        return bullets.stream()
                .map(TracingBullet::fromBullet)
                .collect(Collectors.toList());

    }

}
