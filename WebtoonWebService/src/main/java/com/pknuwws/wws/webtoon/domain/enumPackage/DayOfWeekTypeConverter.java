package com.pknuwws.wws.webtoon.domain.enumPackage;

import jakarta.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class DayOfWeekTypeConverter implements AttributeConverter<DayOfWeekType, String> {

    @Override
    public String convertToDatabaseColumn(DayOfWeekType attribute) {
        return attribute.getCode();
    }


    @Override
    public DayOfWeekType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(DayOfWeekType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 요일입니다."));
    }
}
