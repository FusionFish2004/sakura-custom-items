package cn.sakuratown.jeremyhu.customitems.enchantments;

import cn.sakuratown.jeremyhu.customitems.weapon.Bullet;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;


public class MultishotEnchantment extends GunEnchantment{
    public MultishotEnchantment(){
        setLvl(0);
        setName("三发齐射");
        setCatagory(GunEnchantment.class.getSimpleName());
        setType(this.getClass().getSimpleName());
    }

    @Override
    public List<Bullet> trigger(Player player, List<Bullet> bullets){
        if(bullets.isEmpty()) return bullets;

        Bullet bullet = bullets.stream().findFirst().orElse(null);
        Vector vector = bullet.getDirection().clone().getCrossProduct(new Vector(0,1,0)).normalize().multiply(0.5);
        Bullet b1 = bullet.clone();
        Bullet b2 = bullet.clone();
        b1.setDirection(b1.getDirection().clone().add(vector.clone()).normalize());
        b2.setDirection(b2.getDirection().clone().add(vector.clone().multiply(-1)).normalize());
        bullets.add(b1);
        bullets.add(b2);

        bullets.forEach(b -> {

            bullet.setDamage(b.getDamage() * 0.5);
        });
        return bullets;
    }
}
