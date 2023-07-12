package allchive.server.api.tag.controller;

import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.tag.model.dto.response.AllTagResponse;
import allchive.server.api.tag.service.GetAllTagUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "5. [tag]")
public class TagController {
    private final GetAllTagUseCase getAllTagUseCase;

    @Operation(summary = "모든 태그를 가져옵니다.", description = "latest = true 면 최근 사용한 태그를 가져옵니다.")
    @GetMapping()
    public AllTagResponse createContent(@RequestParam("latest") Boolean latestStatus) {
        return getAllTagUseCase.execute(latestStatus);
    }
}
