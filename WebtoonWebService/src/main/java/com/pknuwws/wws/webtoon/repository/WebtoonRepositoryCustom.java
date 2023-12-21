package com.pknuwws.wws.webtoon.repository;

import com.pknuwws.wws.webtoon.domain.enumPackage.DayOfWeekType;
import com.pknuwws.wws.webtoon.domain.enumPackage.GenreType;
import com.pknuwws.wws.webtoon.dto.WebtoonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WebtoonRepositoryCustom {
    Page<WebtoonResponse> searchWebtoonList(String keyword, GenreType genre, DayOfWeekType dayOfWeek, Pageable pageable);
}
