package allchive.server.api.search.controller;


import allchive.server.api.search.model.dto.request.SearchRequest;
import allchive.server.api.search.model.dto.response.SearchResponse;
import allchive.server.api.search.model.enums.ArchivingType;
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

    @Operation(summary = "아카이빙을 생성합니다.")
    @PostMapping()
    public SearchResponse createArchiving(
            @ParameterObject @PageableDefault(size = 10) Pageable pageable,
            @RequestParam("type") ArchivingType type,
            @RequestBody SearchRequest request) {
        return searchArchivingUseCase.execute(pageable, type, request);
    }
}
