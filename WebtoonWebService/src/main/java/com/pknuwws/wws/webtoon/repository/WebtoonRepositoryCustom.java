package com.pknuwws.wws.webtoon.repository;

import com.pknuwws.wws.webtoon.dto.WebtoonListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WebtoonRepositoryCustom {
    Page<WebtoonListResponse> searchWebtoonList(String keyword, String genre, String week, Pageable pageable);
}
