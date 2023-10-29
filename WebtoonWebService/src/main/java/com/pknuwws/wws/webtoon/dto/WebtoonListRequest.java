package com.pknuwws.wws.webtoon.dto;


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
    private String url; //링크
    private String thumbnailUrl; //썸네일 링크
    private String genre; //장르
    private Integer likeCount; //좋아요
    private Integer overallLikeCount; //전체 좋아요
    private Float likeProportion; // likeCount / overallLikeCount
    private String firstDate; //첫 연재일
    private String  dayOfWeek; // 연재 요일
    private String platform; //플랫폼

}
