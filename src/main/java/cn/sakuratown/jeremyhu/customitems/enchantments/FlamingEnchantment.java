package cn.sakuratown.jeremyhu.customitems.enchantments;

import cn.sakuratown.jeremyhu.customitems.weapon.Bullet;
import cn.sakuratown.jeremyhu.customitems.weapon.FlamingBullet;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class FlamingEnchantment extends GunEnchantment{

    public FlamingEnchantment(){
        setLvl(0);
        setName("火焰伤害");
        setCatagory(GunEnchantment.class.getSimpleName());
        setType(this.getClass().getSimpleName());
    }

    @Override
    public List<Bullet> trigger(Player player, List<Bullet> bullets){
        if(bullets.isEmpty()) return bullets;

        return bullets.stream()
                .map(FlamingBullet::fromBullet)
                .collect(Collectors.toList());

    }
}
