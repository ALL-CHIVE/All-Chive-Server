package allchive.server.api.report.controller;


import allchive.server.api.report.model.dto.request.CreateReportRequest;
import allchive.server.api.report.service.CreateReportUseCase;
import allchive.server.domain.domains.report.domain.enums.ReportObjectType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "7. [report]")
public class ReportController {
    private final CreateReportUseCase createReportUseCase;

    @Operation(summary = "아카이빙, 컨텐츠를 신고합니다.")
    @PostMapping()
    public void createReport(
            @RequestParam("type") ReportObjectType type,
            @RequestBody CreateReportRequest createReportRequest) {
        createReportUseCase.execute(createReportRequest, type);
    }
}
