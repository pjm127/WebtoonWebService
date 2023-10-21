package com.pknuwws.wws.webtoon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Webtoon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //제목
    private String url; //링크
    private String thumbnailUrl; //썸네일 링크
    private String genre; //장르
    private Integer likeCount; //좋아요
    private Integer overallLikeCount; //전체 좋아요
    private Float likeProportion; // likeCount / overallLikeCount
    private String firstDate; //첫 연재일
    private String day; // 연재 요일
    private String platform; //플랫폼



    @Builder.Default
    @OneToMany(mappedBy = "webtoon",cascade = CascadeType.ALL)
    private List<Comment> coments = new ArrayList<>();
}
