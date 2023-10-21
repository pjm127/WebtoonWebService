package com.pknuwws.wws.webtoon.service;

import com.pknuwws.wws.exception.CustomException;
import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.dto.WebtoonListRequest;
import com.pknuwws.wws.webtoon.repository.CommentRepository;
import com.pknuwws.wws.webtoon.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
    public List<WebtoonListRequest> getAllWebtoon(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("likeProportion")));
        Page<Webtoon> allWebtoons = webtoonRepository.findAll(pageable);

        List<WebtoonListRequest> webtoonListRequests = new ArrayList<>();

        for (Webtoon webtoon : allWebtoons) {
            WebtoonListRequest build = WebtoonListRequest.builder()
                    .title(webtoon.getTitle())
                .url(webtoon.getUrl())
                .thumbnailUrl(webtoon.getThumbnailUrl())
                .genre(webtoon.getGenre())
                .likeCount(webtoon.getLikeCount())
                .firstDate(webtoon.getFirstDate())
                .day(webtoon.getDay())
                .platform(webtoon.getPlatform())
                .likeProportion(webtoon.getLikeProportion())
                    .build();

            webtoonListRequests.add(build);
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
            .url(webtoon.getUrl())
            .thumbnailUrl(webtoon.getThumbnailUrl())
            .genre(webtoon.getGenre())
            .likeCount(webtoon.getLikeCount())
            .firstDate(webtoon.getFirstDate())
            .day(webtoon.getDay())
            .platform(webtoon.getPlatform())
                .build();
    }

    //웹툰 한개 선택 댓글용
    public Webtoon getWebtoonForComment(Long id){
        return webtoonRepository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_WEBTOON));
    }


}
