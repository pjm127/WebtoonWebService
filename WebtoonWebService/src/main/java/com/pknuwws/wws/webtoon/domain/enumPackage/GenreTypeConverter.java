package com.pknuwws.wws.webtoon.domain.enumPackage;

import jakarta.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class GenreTypeConverter implements AttributeConverter<GenreType, String> {

    @Override
    public String convertToDatabaseColumn(GenreType attribute) {
        return attribute.getCode();
    }

    @Override
    public GenreType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(GenreType.class).stream()
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 장르입니다."));
    }

}
