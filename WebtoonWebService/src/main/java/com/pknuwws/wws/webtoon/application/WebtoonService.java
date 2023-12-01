package com.pknuwws.wws.webtoon.application;

import com.pknuwws.wws.exception.CustomException;
import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.dto.WebtoonListRequest;
import com.pknuwws.wws.webtoon.repository.WebtoonRepository;

import java.time.LocalDate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pknuwws.wws.exception.ResponseCode.NOT_FOUND_WEBTOON;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;

    //웹툰 전체 목록
    public Page<Webtoon> getAllWebtoonList(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("likeProportion")));
        return webtoonRepository.findAll(pageable);


    }

   //장르별 웹툰 목록
   public Page<Webtoon> getGenreWebtoonList(String genre,int page) {
       Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("likeProportion")));
       return webtoonRepository.findByGenre(genre, pageable);


   }

    public Page<Webtoon> getNewWebtoonList(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("likeProportion")));
        LocalDate localDate = LocalDate.now().minusMonths(3);
        return webtoonRepository.findByFirstDate(localDate, pageable);

    }




    //제목:소녀의 세계
    //썸네일:https://image-comic.pstatic.net/webtoon/654774/thumbnail/thumbnail_IMAG21_4048794550434817075.jpg
    //태그 (장르):로맨스
    //최신 화 링크:https://comic.naver.com/webtoon/detail?titleId=654774&no=384&week=mon
    //최신화 좋아요:11,749
    //1화 날짜: 15.05.17

    //웹툰 한개 선택
    public WebtoonListRequest getWebtoon(Long id){
        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_WEBTOON));
        return WebtoonListRequest.builder()
                .title(webtoon.getTitle())
            .url(webtoon.getUrl())
            .thumbnailUrl(webtoon.getThumbnailUrl())
            .genre(webtoon.getGenre())
            .likeCount(webtoon.getLikeCount())
            .firstDate(webtoon.getFirstDate())
            . dayOfWeek(webtoon.getDayOfWeek())
            .platform(webtoon.getPlatform())
                .build();
    }

    //웹툰 한개 선택 댓글용
    public Webtoon getWebtoonForComment(Long id){
        return webtoonRepository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_WEBTOON));
    }

    //웹툰 검색
    public Page<WebtoonListRequest>  searchWebtoonList(String keyword,String week,String genre,Pageable page){
        return webtoonRepository.searchWebtoonList(keyword,genre,week,page);
    }
}
