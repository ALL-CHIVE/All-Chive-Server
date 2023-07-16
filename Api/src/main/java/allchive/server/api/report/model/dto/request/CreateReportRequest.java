package allchive.server.api.report.model.dto.request;


import allchive.server.core.annotation.ValidEnum;
import allchive.server.domain.domains.report.domain.enums.ReportedType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CreateReportRequest {
    @Schema(defaultValue = "기타기타기타기타기타", description = "사유 ('기타'에만 해당)")
    private String reason;

    @Schema(defaultValue = "spam", description = "신고 종류")
    @ValidEnum(target = ReportedType.class)
    private ReportedType reportedType;

    @Schema(defaultValue = "1", description = "아카이빙, 컨텐츠 고유번호")
    private Long id;
}
