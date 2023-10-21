package com.pknuwws.wws.webtoon.controller;

import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.dto.WebtoonListRequest;
import com.pknuwws.wws.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/webtoon")
public class WebtoonController {
    private final WebtoonService webtoonService;

    //전체 웹툰 랭킹 likeProportion높은거순으로 정렬
    @GetMapping("/list")
    public ResponseEntity<List<WebtoonListRequest>> getAllWebtoon(
        @PageableDefault int page)
    {
        return ResponseEntity.ok(webtoonService.getAllWebtoon(page));
    }




    //웹툰 한개 선택
    @GetMapping("/detail/{webtoonId}")
    public ResponseEntity<WebtoonListRequest> getWebtoon(@PathVariable("webtoonId") Long webtoonId){
        return ResponseEntity.ok(webtoonService.getWebtoon(webtoonId));
    }

}
