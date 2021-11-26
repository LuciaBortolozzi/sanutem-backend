package com.sanutem.backend.enums;

public enum monthsEnum {

    MONTH("DESCRIPTION", 0),
    JANUARY("January", 1),
    FEBRUARY("February", 2),
    MARCH("March", 3),
    APRIL("April", 4),
    MAY("May", 5),
    JUNE("June", 6),
    JULY("July", 7),
    AUGUST("August", 8),
    SEPTEMBER("September", 9),
    OCTOBER("October", 10),
    NOVEMBER("November", 11),
    DECEMBER("December", 12);

    private String monthName;
    private Integer monthValue;

    monthsEnum() {
    }

    private monthsEnum(String monthName, Integer monthValue) {
        this.monthName = monthName;
        this.monthValue = monthValue;
    }

    public Integer findMonthValueByMonthName(String monthName) {
        if (monthName.equals(JANUARY.getMonthName())) {
            return JANUARY.getMonthValue();
        } else if (monthName.equals(FEBRUARY.getMonthName())) {
            return FEBRUARY.getMonthValue();
        } else if (monthName.equals(MARCH.getMonthName())) {
            return MARCH.getMonthValue();
        } else if (monthName.equals(APRIL.getMonthName())) {
            return APRIL.getMonthValue();
        } else if (monthName.equals(MAY.getMonthName())) {
            return MAY.getMonthValue();
        } else if (monthName.equals(JUNE.getMonthName())) {
            return JUNE.getMonthValue();
        } else if (monthName.equals(JULY.getMonthName())) {
            return JULY.getMonthValue();
        } else if (monthName.equals(AUGUST.getMonthName())) {
            return AUGUST.getMonthValue();
        } else if (monthName.equals(SEPTEMBER.getMonthName())) {
            return SEPTEMBER.getMonthValue();
        } else if (monthName.equals(OCTOBER.getMonthName())) {
            return OCTOBER.getMonthValue();
        } else if (monthName.equals(NOVEMBER.getMonthName())) {
            return NOVEMBER.getMonthValue();
        } else if (monthName.equals(DECEMBER.getMonthName())) {
            return DECEMBER.getMonthValue();
        } else {
            return null;
        }
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Integer getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(Integer monthValue) {
        this.monthValue = monthValue;
    }
}
