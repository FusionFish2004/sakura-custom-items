package cn.sakuratown.jeremyhu.customitems.enchantments;

import cn.sakuratown.jeremyhu.customitems.weapon.Bullet;
import cn.sakuratown.jeremyhu.customitems.weapon.OrbitalBullet;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OrbitalEnchantment extends GunEnchantment{
    public OrbitalEnchantment(){
        setLvl(0);
        setName("小小星球");
        setCatagory(GunEnchantment.class.getSimpleName());
        setType(this.getClass().getSimpleName());
    }

    @Override
    public List<Bullet> trigger(Player player, List<Bullet> bullets){
        if(bullets.isEmpty()) return bullets;

        return bullets.stream()
                .map(OrbitalBullet::fromBullet)
                .collect(Collectors.toList());

    }
}
