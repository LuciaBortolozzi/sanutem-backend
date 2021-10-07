package com.sanutem.backend.enums;

public enum timeRangeEnum {

    RANGE_07_08("range_1","07-08"),
    RANGE_08_09("range_2","08-09"),
    RANGE_09_10("range_3","09-10"),
    RANGE_10_11("range_4","10-11"),
    RANGE_11_12("range_5","11-12"),
    RANGE_12_13("range_6","12-13"),
    RANGE_13_14("range_7","13-14"),
    RANGE_14_15("range_8","14-15"),
    RANGE_15_16("range_9","15-16"),
    RANGE_16_17("range_10","16-17"),
    RANGE_17_18("range_11","17-18"),
    RANGE_18_19("range_12","18-19"),
    RANGE_19_20("range_13","19-20");

    private String rageName;
    private String rageValue;

    timeRangeEnum() {}

    private timeRangeEnum (String rageName, String rageValue){
        this.rageName = rageName;
        this.rageValue = rageValue;
    }

    public String getRageName() {
        return rageName;
    }

    public void setRageName(String rageName) {
        this.rageName = rageName;
    }

    public String getRageValue() {
        return rageValue;
    }

    public void setRageValue(String rageValue) {
        this.rageValue = rageValue;
    }
}
