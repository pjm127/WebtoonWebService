package com.pknuwws.wws.webtoon.dto;


import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.domain.enumPackage.DayOfWeekType;
import com.pknuwws.wws.webtoon.domain.enumPackage.GenreType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class WebtoonResponse {

    private Long id;
    private String title; //제목
    private String url; //링크
    private String thumbnailUrl; //썸네일 링크
    private GenreType genre; //장르
    private Integer likeCount; //좋아요
    private Integer overallLikeCount; //전체 좋아요
    private Float likeProportion; // likeCount / overallLikeCount
    private LocalDate firstDate; //첫 연재일
    private DayOfWeekType dayOfWeek; // 연재 요일
    private String platform; //플랫폼


    @Builder
    @QueryProjection
    public WebtoonResponse(Long id, String title, String url, String thumbnailUrl, GenreType genre,
                               Integer likeCount, Integer overallLikeCount, Float likeProportion, LocalDate firstDate,
                               DayOfWeekType dayOfWeek, String platform) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.genre = genre;
        this.likeCount = likeCount;
        this.overallLikeCount = overallLikeCount;
        this.likeProportion = likeProportion;
        this.firstDate = firstDate;
        this.dayOfWeek = dayOfWeek;
        this.platform = platform;
    }

    public static WebtoonResponse of(Webtoon webtoon){
        return WebtoonResponse.builder()
                .id(webtoon.getId())
                .title(webtoon.getTitle())
                .url(webtoon.getUrl())
                .thumbnailUrl(webtoon.getThumbnailUrl())
                .genre(webtoon.getGenre())
                .likeCount(webtoon.getLikeCount())
                .overallLikeCount(webtoon.getOverallLikeCount())
                .likeProportion(webtoon.getLikeProportion())
                .firstDate(webtoon.getFirstDate())
                .dayOfWeek(webtoon.getDayOfWeek())
                .platform(webtoon.getPlatform())
                .build();
    }
}
