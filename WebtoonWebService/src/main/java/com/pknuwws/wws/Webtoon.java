
package com.pknuwws.wws;

import jakarta.persistence.Entity;
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
	private Long id;
	
	private String title;
	
	private String url;
	
	private String thumbnailUrl;
	
	private String genre;
	
	private Integer likeCount;
	
	private Integer overallLikeCount;
	
	private Float likePerOverall;
	
	private String firstDate;
	
	private String day;
	
	private String platform;

}
