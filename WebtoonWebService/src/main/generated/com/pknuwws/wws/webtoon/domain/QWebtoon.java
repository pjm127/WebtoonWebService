package com.pknuwws.wws.webtoon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebtoon is a Querydsl query type for Webtoon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoon extends EntityPathBase<Webtoon> {

    private static final long serialVersionUID = -1655364695L;

    public static final QWebtoon webtoon = new QWebtoon("webtoon");

    public final StringPath author = createString("author");

    public final ListPath<Comment, QComment> coments = this.<Comment, QComment>createList("coments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath dayOfWeek = createString("dayOfWeek");

    public final DatePath<java.time.LocalDate> firstDate = createDate("firstDate", java.time.LocalDate.class);

    public final StringPath genre = createString("genre");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final NumberPath<Float> likeProportion = createNumber("likeProportion", Float.class);

    public final NumberPath<Integer> overallLikeCount = createNumber("overallLikeCount", Integer.class);

    public final StringPath platform = createString("platform");

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final StringPath title = createString("title");

    public final StringPath url = createString("url");

    public QWebtoon(String variable) {
        super(Webtoon.class, forVariable(variable));
    }

    public QWebtoon(Path<? extends Webtoon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWebtoon(PathMetadata metadata) {
        super(Webtoon.class, metadata);
    }

}

