package cn.sakuratown.jeremyhu.customitems.enchantments;

import cn.sakuratown.jeremyhu.customitems.weapon.Bullet;
import org.bukkit.entity.Player;

import java.util.List;

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
    public void trigger(Player player, List<Bullet> bullets){
        player.sendMessage("track");
        if(bullets.isEmpty()) return;
        /*
        bullets.stream()
                .map(bullet -> (TracingBullet)bullet)
                .forEach(TracingBullet::run);

         */
    }
}
