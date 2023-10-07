package com.pknuwws.wws.webtoon.dto;

import com.pknuwws.wws.webtoon.domain.PublishingDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebtoonListRequest {
    private String title; //제목
    private String link; //링크
    private String thumbnail; //썸네일
    private Integer love; //좋아요
    private String genre; //장르
    private LocalDate firstEpisodeDay; //첫회 연재일
    private PublishingDay publishingDay; //연재 요일
}
