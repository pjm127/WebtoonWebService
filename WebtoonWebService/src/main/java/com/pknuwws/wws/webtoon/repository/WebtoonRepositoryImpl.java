package com.pknuwws.wws.webtoon.repository;

import com.pknuwws.wws.webtoon.dto.WebtoonListRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;

public class WebtoonRepositoryImpl implements WebtoonRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public WebtoonRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<WebtoonListRequest> searchWebtoonList(String keyword, String genre, String week) {
        return null;
    }
}
