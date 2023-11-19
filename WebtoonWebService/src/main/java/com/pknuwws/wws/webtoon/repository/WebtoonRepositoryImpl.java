package com.pknuwws.wws.webtoon.repository;

import static com.pknuwws.wws.webtoon.domain.QWebtoon.webtoon;
import static io.jsonwebtoken.lang.Collections.isEmpty;
import static org.springframework.util.StringUtils.hasText;

import com.pknuwws.wws.webtoon.dto.QWebtoonListRequest;
import com.pknuwws.wws.webtoon.dto.WebtoonListRequest;
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
    public Page<WebtoonListRequest> searchWebtoonList(String keyword, String genre, String week, Pageable pageable) {

        QueryResults<WebtoonListRequest> results = queryFactory.select(
                        new QWebtoonListRequest(webtoon.title, webtoon.url, webtoon.thumbnailUrl, webtoon.genre,
                                webtoon.likeCount, webtoon.overallLikeCount, webtoon.likeProportion, webtoon.firstDate,
                                webtoon.dayOfWeek
                                , webtoon.platform))
                .from(webtoon).where(genreEq(genre), weekEq(week), genreLike(keyword)).fetchResults();
        List<WebtoonListRequest> results1 = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(results1, pageable, total);

    }
    private BooleanExpression genreEq(String genre) {
        return hasText(genre) ? null : webtoon.genre.eq(genre);
    }
    private BooleanExpression weekEq(String week) {
        return hasText(week) ? null : webtoon.genre.eq(week);
    }
    private BooleanExpression genreLike(String keyword) {
        return hasText(keyword) ? webtoon.title.like("%" + keyword + "%") : null;
    }
}
