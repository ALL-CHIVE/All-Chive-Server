package allchive.server.api.archiving.controller;


import allchive.server.api.archiving.model.dto.request.CreateArchivingRequest;
import allchive.server.api.archiving.model.dto.request.UpdateArchivingRequest;
import allchive.server.api.archiving.model.dto.response.ArchivingContentsResponse;
import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.api.archiving.model.dto.response.ArchivingTitleResponse;
import allchive.server.api.archiving.service.*;
import allchive.server.api.common.slice.SliceResponse;
import allchive.server.domain.domains.archiving.domain.enums.Subject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/archivings")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "3. [archiving]")
public class ArchivingController {
    private final CreateArchivingUseCase createArchivingUseCase;
    private final UpdateArchivingUseCase updateArchivingUseCase;
    private final DeleteArchivingUseCase deleteArchivingUseCase;
    private final GetArchivingUseCase getArchivingUseCase;
    private final GetArchivedArchivingUseCase getArchivedArchivingUseCase;
    private final GetScrapArchivingUseCase getScrapArchivingUseCase;
    private final GetArchivingTitleUseCase getArchivingTitleUseCase;
    private final GetArchivingContentsUseCase getArchivingContentsUseCase;
    private final UpdateArchivingScrapUseCase updateArchivingScrapUseCase;
    private final UpdateArchivingPinUseCase updateArchivingPinUseCase;

    @Operation(summary = "아카이빙을 생성합니다.")
    @PostMapping()
    public void createArchiving(@RequestBody CreateArchivingRequest createArchivingRequest) {
        createArchivingUseCase.execute(createArchivingRequest);
    }

    @Operation(summary = "아카이빙을 수정합니다.")
    @PatchMapping(value = "/{archivingId}")
    public void updateArchiving(
            @PathVariable("archivingId") Long archivingId,
            @RequestBody UpdateArchivingRequest updateArchivingRequest) {
        updateArchivingUseCase.execute(archivingId, updateArchivingRequest);
    }

    @Operation(summary = "아카이빙을 삭제합니다.")
    @DeleteMapping(value = "/{archivingId}")
    public void deleteArchiving(@PathVariable("archivingId") Long archivingId) {
        deleteArchivingUseCase.execute(archivingId);
    }

    @Operation(
            summary = "주제별 아카이빙 리스트를 가져옵니다.",
            description = "sort parameter는 입력하지 말아주세요! sorting : 스크랩 여부 -> 스크랩 수 -> 생성일자")
    @GetMapping()
    public SliceResponse<ArchivingResponse> getArchiving(
            @RequestParam("subject") Subject subject,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return getArchivingUseCase.execute(subject, pageable);
    }

    @Operation(
            summary = "내 아카이빙 주제별 아카이빙 리스트를 가져옵니다.",
            description = "sort parameter는 입력하지 말아주세요! sorting : 고정 -> 스크랩 수 -> 생성일자")
    @GetMapping(value = "/me/archiving")
    public SliceResponse<ArchivingResponse> getArchivedArchiving(
            @RequestParam("subject") Subject subject,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return getArchivedArchivingUseCase.execute(subject, pageable);
    }

    @Operation(
            summary = "스크랩 주제별 아카이빙 리스트를 가져옵니다.",
            description = "sort parameter는 입력하지 말아주세요! sorting : 스크랩 수 -> 생성일자")
    @GetMapping(value = "/me/scrap")
    public SliceResponse<ArchivingResponse> getScrapArchiving(
            @RequestParam("subject") Subject subject,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return getScrapArchivingUseCase.execute(subject, pageable);
    }

    @Operation(summary = "사용 중인 주제 & 아카이빙 리스트를 가져옵니다. (컨텐츠 추가 시 사용)")
    @GetMapping(value = "/lists")
    public ArchivingTitleResponse getScrapArchiving() {
        return getArchivingTitleUseCase.execute();
    }

    @Operation(summary = "아카이빙별 컨텐츠 리스트를 가져옵니다.")
    @GetMapping(value = "/{archivingId}/contents")
    public ArchivingContentsResponse getArchivingContents(
            @PathVariable("archivingId") Long archivingId,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return getArchivingContentsUseCase.execute(archivingId, pageable);
    }

    @Operation(summary = "아카이빙을 스크랩합니다.", description = "스크랩 취소면 cancel에 true 값 보내주세요")
    @PatchMapping(value = "/{archivingId}/scrap")
    public void updateArchivingScrap(
            @RequestParam("cancel") Boolean cancel, @PathVariable("archivingId") Long archivingId) {
        updateArchivingScrapUseCase.execute(archivingId, cancel);
    }

    @Operation(summary = "아카이빙을 고정합니다.", description = "고정 취소면 cancel에 true 값 보내주세요")
    @PatchMapping(value = "/{archivingId}/pin")
    public void updateArchivingPin(
            @RequestParam("cancel") Boolean cancel, @PathVariable("archivingId") Long archivingId) {
        updateArchivingPinUseCase.execute(archivingId, cancel);
    }
}
