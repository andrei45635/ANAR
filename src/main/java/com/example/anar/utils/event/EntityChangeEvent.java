package com.example.anar.utils.event;

import com.example.anar.domain.River;

public class EntityChangeEvent implements Event {
    private ChangeEventType type;
    private River data;
    private River old_data;

    public EntityChangeEvent(ChangeEventType type, River data, River old_data) {
        this.type = type;
        this.data = data;
        this.old_data = old_data;
    }

    public EntityChangeEvent(ChangeEventType type, River data) {
        this.type = type;
        this.data = data;
    }

    public ChangeEventType getType() {
        return type;
    }

    public River getData() {
        return data;
    }

    public River getOld_data() {
        return old_data;
    }

    //old + new data of the Entity + constructors and getter
}
