package com.pknuwws.wws.webtoon.repository;

import static com.pknuwws.wws.webtoon.domain.QWebtoon.webtoon;
import static io.jsonwebtoken.lang.Collections.isEmpty;
import static org.springframework.util.StringUtils.hasText;


import com.pknuwws.wws.webtoon.domain.enumPackage.DayOfWeekType;
import com.pknuwws.wws.webtoon.domain.enumPackage.GenreType;
import com.pknuwws.wws.webtoon.dto.QWebtoonListResponse;
import com.pknuwws.wws.webtoon.dto.WebtoonListResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class WebtoonRepositoryImpl implements WebtoonRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public WebtoonRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<WebtoonListResponse> searchWebtoonList(String keyword, GenreType genre, DayOfWeekType dayOfWeek, Pageable pageable) {


        QueryResults<WebtoonListResponse> results = queryFactory.select(
                        new QWebtoonListResponse(webtoon.id ,webtoon.title, webtoon.url, webtoon.thumbnailUrl, webtoon.genre,
                                webtoon.likeCount, webtoon.overallLikeCount, webtoon.likeProportion, webtoon.firstDate,
                                webtoon.dayOfWeek
                                , webtoon.platform))
                .from(webtoon).where(genreEq(genre), weekEq(dayOfWeek), genreLike(keyword)).fetchResults();
        List<WebtoonListResponse> results1 = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(results1, pageable, total);

    }
    private BooleanExpression genreEq(GenreType genre) {
        return genre != null ? webtoon.genre.eq(genre) : null;
    }
    private BooleanExpression weekEq(DayOfWeekType dayOfWeek) {
        return dayOfWeek != null ? webtoon.dayOfWeek.eq(dayOfWeek) : null;
    }
    private BooleanExpression genreLike(String keyword) {
        return hasText(keyword) ? webtoon.title.like("%" + keyword + "%") : null;
    }
}
