package cn.sakuratown.jeremyhu.customitems.enchantments;

import cn.sakuratown.jeremyhu.customitems.weapon.Bullet;
import cn.sakuratown.jeremyhu.customitems.weapon.VampireBullet;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class VampireEnchantment extends GunEnchantment{

    public VampireEnchantment(){
        setLvl(0);
        setName("生命吸取");
        setCatagory(GunEnchantment.class.getSimpleName());
        setType(this.getClass().getSimpleName());
    }

    @Override
    public List<Bullet> trigger(Player player, List<Bullet> bullets){
        if(bullets.isEmpty()) return bullets;

        return bullets.stream()
                .map(VampireBullet::fromBullet)
                .collect(Collectors.toList());

    }
}
