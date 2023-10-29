
package com.pknuwws.wws.webtoon.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO
// @Data를 @Getter로 바꿔야 함 -> crawlingWebtoonInfos 클래스들 안의 메소드 호출도 바꿔야 함
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Webtoon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String url;
	private String thumbnailUrl;
	private String genre;
	private Integer likeCount;
	private Integer overallLikeCount;
	private Float likeProportion; // likeCount / overallLikeCount
	private LocalDate firstDate;
	private String dayOfWeek; // 연재 요일
	private String platform;



	@Builder.Default
	@OneToMany(mappedBy = "webtoon",cascade = CascadeType.ALL)
	private List<Comment> coments = new ArrayList<>();
}
