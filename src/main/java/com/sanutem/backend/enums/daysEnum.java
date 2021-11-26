package com.sanutem.backend.enums;

public enum daysEnum {

    DAY("Description", 0),
    MONDAY("Monday", 2),
    TUESDAY("Tuesday", 3),
    WEDNESDAY("Wednesday", 4),
    THURSDAY("Thursday", 5),
    FRIDAY("Friday", 6),
    SATURDAY("Saturday", 7),
    SUNDAY("Sunday", 1);

    private String dayName;
    private Integer dayValue;

    daysEnum() {
    }

    private daysEnum(String dayName, Integer dayValue) {
        this.dayName = dayName;
        this.dayValue = dayValue;
    }

    public String findDayNameByDayValue(Integer dayValue) {
        if (dayValue.equals(MONDAY.getDayValue())) {
            return MONDAY.getDayName();
        } else if (dayValue.equals(TUESDAY.getDayValue())) {
            return TUESDAY.getDayName();
        } else if (dayValue.equals(WEDNESDAY.getDayValue())) {
            return WEDNESDAY.getDayName();
        } else if (dayValue.equals(THURSDAY.getDayValue())) {
            return THURSDAY.getDayName();
        } else if (dayValue.equals(FRIDAY.getDayValue())) {
            return FRIDAY.getDayName();
        } else if (dayValue.equals(SATURDAY.getDayValue())) {
            return SATURDAY.getDayName();
        } else if (dayValue.equals(SUNDAY.getDayValue())) {
            return SUNDAY.getDayName();
        } else {
            return null;
        }
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Integer getDayValue() {
        return dayValue;
    }

    public void setDayValue(Integer dayValue) {
        this.dayValue = dayValue;
    }
}
