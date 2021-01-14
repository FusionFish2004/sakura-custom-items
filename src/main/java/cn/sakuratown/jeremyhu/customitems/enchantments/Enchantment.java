package cn.sakuratown.jeremyhu.customitems.enchantments;

import org.bukkit.entity.Player;

public class Enchantment {
    private String type;
    private int lvl;
    private String name;
    private String catagory;

    public int getLvl() {
        return lvl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getType() {
        return type;
    }

    public void trigger(Player player){

    }

    public boolean isGunEnchantment(){
        return false;
    }

    @Override
    public String toString(){
        return this.name + getLvl(lvl);
    }

    public static String getLvl(int lvl){
        //罗马数字转换
        switch (lvl){
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
        }
        return "";
    }
}
