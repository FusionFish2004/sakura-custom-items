package cn.sakuratown.jeremyhu.customitems.items;

import java.util.List;

public class Meta {
    private String name;
    private int CD;
    private List<Item.Enchantment> enchantments;

    public Meta(String name, int CD, List<Item.Enchantment> enchantments){
        this.name = name;
        this.CD = CD;
        this.enchantments = enchantments;
    }

    public Meta(){

    }

    public List<Item.Enchantment> getEnchantments() {
        return enchantments;
    }

    public int getCD() {
        return CD;
    }

    public String getName() {
        return name;
    }

    public void setCD(int CD) {
        this.CD = CD;
    }

    public void setEnchantments(List<Item.Enchantment> enchantments) {
        this.enchantments = enchantments;
    }

    public void setName(String name) {
        this.name = name;
    }
}
