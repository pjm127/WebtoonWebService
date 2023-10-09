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
    private String link; //링크
    private String thumbnail; //썸네일
    private Integer love; //좋아요
    private String genre; //장르
    private LocalDate firstEpisodeDay; //첫회 연재일
    private PublishingDay  publishingDay; //연재 요일




    @Builder.Default
    @OneToMany(mappedBy = "webtoon",cascade = CascadeType.ALL)
    private List<Comment> coments = new ArrayList<>();
}
