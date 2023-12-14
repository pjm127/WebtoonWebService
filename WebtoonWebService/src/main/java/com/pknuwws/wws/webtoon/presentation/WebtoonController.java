package com.pknuwws.wws.webtoon.presentation;

import com.pknuwws.wws.webtoon.domain.Webtoon;
import com.pknuwws.wws.webtoon.domain.enumPackage.DayOfWeekType;
import com.pknuwws.wws.webtoon.domain.enumPackage.GenreType;
import com.pknuwws.wws.webtoon.dto.WebtoonListResponse;
import com.pknuwws.wws.webtoon.application.WebtoonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/webtoon")
public class WebtoonController {
    private final WebtoonService webtoonService;

    //3. 전체 웹툰 랭킹 likeProportion높은거순으로 정렬
    @GetMapping("/list")
    @Operation(summary = "전체 웹툰 랭킹")
    private ResponseEntity< Page<WebtoonListResponse>> getAllWebtoon(
        @PageableDefault int page)
    {
        return ResponseEntity.ok(webtoonService.getAllWebtoonList(page));
    }

    //웹툰 한개 선택
    @GetMapping("/detail/{webtoonId}")
    @Operation(summary = "웹툰 한개 선택")
    private ResponseEntity<WebtoonListResponse> getWebtoon(@PathVariable("webtoonId") Long webtoonId){
        return ResponseEntity.ok(webtoonService.getWebtoon(webtoonId));
    }

    //4. 장르별 웹툰 랭킹 확인
    @GetMapping("/genre-list")
    @Operation(summary = "장르별 웹툰 랭킹 확인")
    private ResponseEntity< Page<WebtoonListResponse>> getGenreWebtoon(
        @RequestParam("genre") GenreType genre,
        @PageableDefault @Parameter(name="page") int page)
    {
        return ResponseEntity.ok(webtoonService.getGenreWebtoonList(genre, page));
    }

    //5. 신작 웹툰 확인
    @GetMapping("/new-list")
    @Operation(summary = "신작 웹툰 랭킹")
    private ResponseEntity< Page<WebtoonListResponse>> getNewWebtoon(
        @PageableDefault @Parameter(name="page") int page)
    {
        return ResponseEntity.ok(webtoonService.getNewWebtoonList(page));
    }
    //7. 검색
    @GetMapping("/search")
    @Operation(summary = "장르, 요일, 검색어로 검색")
    private ResponseEntity<Page<WebtoonListResponse>> searchWebtoon(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "genre", required = false) GenreType genre,
            @RequestParam(value = "week", required = false) DayOfWeekType week,
        @PageableDefault Pageable page)
    {
        return ResponseEntity.ok(webtoonService.searchWebtoonList(keyword, week, genre, page)) ;
    }

}
