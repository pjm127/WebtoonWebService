package com.pknuwws.wws.webtoon.domain.enumPackage;


import com.pknuwws.wws.config.CodeValue;

public enum GenreType implements CodeValue {
    ROMANCE("0","로맨스"),
    ACTION("1","액션"),
    FANTASY("2","판타지"),
    MARTIAL_ARTS("3","무협"),
    SPORTS("4","스포츠"),
    THRILLER("5","스릴러"),
    DAILY_LIFE("6","일상"),
    ETC("7","기타");



    private final String code;
    private final String value;

    GenreType(String code, String value) {
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
    public static GenreType fromCode(String code) {
        for (GenreType status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for code: " + code);
    }
}
