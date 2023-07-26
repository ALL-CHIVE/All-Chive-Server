package allchive.server.api.search.controller;


import allchive.server.api.search.model.dto.request.SearchRequest;
import allchive.server.api.search.model.dto.response.SearchListResponse;
import allchive.server.api.search.model.dto.response.SearchResponse;
import allchive.server.api.search.model.dto.response.SearchVoListResponse;
import allchive.server.api.search.model.enums.ArchivingType;
import allchive.server.api.search.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/searches")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "9. [search]")
public class SearchController {
    private final SearchArchivingUseCase searchArchivingUseCase;
    private final GetLatestSearchListUseCase getLatestSearchListUseCase;
    private final GetRelativeSearchListUseCase getRelativeSearchListUseCase;
    private final DeleteLatestSearchUseCase deleteLatestSearchUseCase;
    private final RenewalTitleDataUseCase renewalTitleDataUseCase;

    @Operation(summary = "검색어를 검색합니다.")
    @GetMapping
    public SearchResponse searchArchiving(
            @ParameterObject @PageableDefault(size = 10) Pageable pageable,
            @RequestParam("type") ArchivingType type,
            @RequestParam("word") String word) {
        return searchArchivingUseCase.execute(pageable, type, word);
    }

    @Operation(summary = "최근 검색어 목록을 가져옵니다.", description = "5개만 드릴게요")
    @GetMapping(value = "/latest")
    public SearchVoListResponse getLatestSearchList() {
        return getLatestSearchListUseCase.execute();
    }

    @Operation(summary = "최근 검색어를 삭제합니다.")
    @DeleteMapping(value = "/latest/{latestId}")
    public void deleteLatestSearch(@PathVariable("latestId") Long latestId) {
        deleteLatestSearchUseCase.execute(latestId);
    }

    @Operation(summary = "검색어 자동 완성")
    @GetMapping(value = "/relation")
    public SearchListResponse getRelativeSearchList(@RequestParam("word") String word) {
        return getRelativeSearchListUseCase.execute(word);
    }

    @Operation(summary = "자동 완성 데이터 강제 리뉴얼", deprecated = true)
    @GetMapping(value = "/relation/force")
    public void forceRenewalTitleData() {
        renewalTitleDataUseCase.executeForce();
    }
}
