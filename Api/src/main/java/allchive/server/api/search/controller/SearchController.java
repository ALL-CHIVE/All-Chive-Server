package allchive.server.api.search.controller;


import allchive.server.api.search.model.dto.request.SearchRequest;
import allchive.server.api.search.model.dto.response.SearchListResponse;
import allchive.server.api.search.model.dto.response.SearchResponse;
import allchive.server.api.search.model.enums.ArchivingType;
import allchive.server.api.search.service.GetLatestSearchListUseCase;
import allchive.server.api.search.service.GetRelativeSearchListUseCase;
import allchive.server.api.search.service.SearchArchivingUseCase;
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

    @Operation(summary = "검색어를 검색합니다.")
    @PostMapping
    public SearchResponse searchArchiving(
            @ParameterObject @PageableDefault(size = 10) Pageable pageable,
            @RequestParam("type") ArchivingType type,
            @RequestBody SearchRequest request) {
        return searchArchivingUseCase.execute(pageable, type, request);
    }

    @Operation(summary = "최근 검색어 목록을 가져옵니다.", description = "5개만 드릴게요")
    @GetMapping(value = "/latest")
    public SearchListResponse getLatestSearchList() {
        return getLatestSearchListUseCase.execute();
    }

    @Operation(summary = "검색어 자동 완성")
    @PostMapping(value = "/relation")
    public SearchListResponse getRelativeSearchList(@RequestBody SearchRequest request) {
        return getRelativeSearchListUseCase.execute(request);
    }
}
