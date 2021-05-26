package com.my.enums;

public enum OrderType {
    ON_TICKET("on_ticket"),IN_HALL("in_hall");

    String type;
    OrderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.replace("_"," ");
    }
}
