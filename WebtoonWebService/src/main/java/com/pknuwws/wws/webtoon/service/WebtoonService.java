package com.pknuwws.wws.webtoon.service;

import com.pknuwws.wws.exception.CustomException;
import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.dto.WebtoonListRequest;
import com.pknuwws.wws.webtoon.repository.CommentRepository;
import com.pknuwws.wws.webtoon.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pknuwws.wws.exception.ResponseCode.NOT_FOUND_WEBTOON;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;

    //웹툰 전체 목록
    public List<WebtoonListRequest> getAllWebtoon() {
        List<Webtoon> allWebtoons = webtoonRepository.findAll();
        List<WebtoonListRequest> webtoonListRequests = new ArrayList<>();

        for (Webtoon webtoon : allWebtoons) {
            WebtoonListRequest webtoonListRequest = new WebtoonListRequest();
            // 여기서 Webtoon 엔터티의 필드 값을 WebtoonListRequest DTO에 매핑
            webtoonListRequest.setTitle(webtoon.getTitle());
            webtoonListRequest.setGenre(webtoon.getGenre());
            // 나머지 필드들도 매핑

            webtoonListRequests.add(webtoonListRequest);
        }

        return webtoonListRequests;
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
                .link(webtoon.getLink())
                .thumbnail(webtoon.getThumbnail())
                .love(webtoon.getLove())
                .genre(webtoon.getGenre())
                .firstEpisodeDay(webtoon.getFirstEpisodeDay())
                .publishingDay(webtoon.getPublishingDay())
                .build();
    }

    //댓글용 웹툰 선택
    public Webtoon getWebtoonForComment(Long id){
        return webtoonRepository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_WEBTOON));
    }


}
