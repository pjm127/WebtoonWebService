package com.pknuwws.wws.webtoon.service;

import com.pknuwws.wws.exception.CustomException;
import com.pknuwws.wws.webtoon.domain.Comment;
import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.repository.CommentRepository;
import com.pknuwws.wws.webtoon.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.pknuwws.wws.exception.ResponseCode.NOT_FOUND_WEBTOON;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;

    public void getAllWebtoon() {
        webtoonRepository.findAll();
    }
    public Webtoon getWebtoon(Long id){
        return webtoonRepository.findById(id).orElseThrow(()->new CustomException(NOT_FOUND_WEBTOON));
    }
}
