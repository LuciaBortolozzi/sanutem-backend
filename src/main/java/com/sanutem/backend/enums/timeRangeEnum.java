package com.sanutem.backend.enums;

public enum timeRangeEnum {

    RANGETIME("Description","Value"),
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

    public String findTimeRangeValueByTimeRangeName(String rageName){
        if(rageName.equals(RANGE_07_08.getRageName())){return RANGE_07_08.getRageValue();}
        else if(rageName.equals(RANGE_08_09.getRageName())){return RANGE_08_09.getRageValue();}
        else if(rageName.equals(RANGE_09_10.getRageName())){return RANGE_09_10.getRageValue();}
        else if(rageName.equals(RANGE_10_11.getRageName())){return RANGE_10_11.getRageValue();}
        else if(rageName.equals(RANGE_11_12.getRageName())){return RANGE_11_12.getRageValue();}
        else if(rageName.equals(RANGE_12_13.getRageName())){return RANGE_12_13.getRageValue();}
        else if(rageName.equals(RANGE_13_14.getRageName())){return RANGE_13_14.getRageValue();}
        else if(rageName.equals(RANGE_14_15.getRageName())){return RANGE_14_15.getRageValue();}
        else if(rageName.equals(RANGE_15_16.getRageName())){return RANGE_15_16.getRageValue();}
        else if(rageName.equals(RANGE_16_17.getRageName())){return RANGE_16_17.getRageValue();}
        else if(rageName.equals(RANGE_17_18.getRageName())){return RANGE_17_18.getRageValue();}
        else if(rageName.equals(RANGE_18_19.getRageName())){return RANGE_18_19.getRageValue();}
        else if(rageName.equals(RANGE_19_20.getRageName())){return RANGE_19_20.getRageValue();}
        else {return null;}
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
