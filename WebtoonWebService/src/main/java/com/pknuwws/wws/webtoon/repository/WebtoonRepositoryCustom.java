package com.pknuwws.wws.webtoon.repository;

import com.pknuwws.wws.webtoon.dto.WebtoonListRequest;
import org.springframework.data.domain.Page;

public interface WebtoonRepositoryCustom {
    Page<WebtoonListRequest> searchWebtoonList(String keyword,String genre, String week);
}
