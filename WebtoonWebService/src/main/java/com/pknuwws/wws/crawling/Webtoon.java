
package com.pknuwws.wws.crawling;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
