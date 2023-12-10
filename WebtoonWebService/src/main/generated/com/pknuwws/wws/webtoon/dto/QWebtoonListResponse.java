package com.pknuwws.wws.webtoon.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.pknuwws.wws.webtoon.dto.QWebtoonListResponse is a Querydsl Projection type for WebtoonListResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QWebtoonListResponse extends ConstructorExpression<WebtoonListResponse> {

    private static final long serialVersionUID = -1009084423L;

    public QWebtoonListResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> url, com.querydsl.core.types.Expression<String> thumbnailUrl, com.querydsl.core.types.Expression<String> genre, com.querydsl.core.types.Expression<Integer> likeCount, com.querydsl.core.types.Expression<Integer> overallLikeCount, com.querydsl.core.types.Expression<Float> likeProportion, com.querydsl.core.types.Expression<java.time.LocalDate> firstDate, com.querydsl.core.types.Expression<String> dayOfWeek, com.querydsl.core.types.Expression<String> platform) {
        super(WebtoonListResponse.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, int.class, int.class, float.class, java.time.LocalDate.class, String.class, String.class}, id, title, url, thumbnailUrl, genre, likeCount, overallLikeCount, likeProportion, firstDate, dayOfWeek, platform);
    }

}

