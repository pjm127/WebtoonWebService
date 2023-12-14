package com.pknuwws.wws.webtoon.domain.enumPackage;


import com.pknuwws.wws.config.CodeValue;

public enum DayOfWeekType implements CodeValue {

    MONDAY("0", "월요일"),
    TUESDAY("1", "화요일"),
    WEDNESDAY("2", "수요일"),
    THURSDAY("3", "목요일"),
    FRIDAY("4", "금요일"),
    SATURDAY("5", "토요일"),
    SUNDAY("6", "일요일");


    private final String code;
    private final String value;

    DayOfWeekType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static DayOfWeekType fromCode(String code) {
        for (DayOfWeekType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant for code: " + code);
    }
}
