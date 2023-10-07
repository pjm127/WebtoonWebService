package com.pknuwws.wws.webtoon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String title;
    private String week;
    private String genre;

    @OneToMany(mappedBy = "webtoon",cascade = CascadeType.ALL)
    private List<Comment> coments = new ArrayList<>();
}
