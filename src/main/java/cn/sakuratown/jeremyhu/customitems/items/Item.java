package cn.sakuratown.jeremyhu.customitems.items;

import java.util.UUID;

public class Item {

    private String name = "";
    private String type = "";
    private String uuid = "";

    public Item(UUID uuid){
        this.uuid = uuid.toString();
    }

    public Item(){
        this.uuid = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return UUID.fromString(uuid);
    }

    public void trigger(){

    }
}
