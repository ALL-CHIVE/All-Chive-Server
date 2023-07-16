package allchive.server.domain.domains.report.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.report.domain.enums.ReportObjectType;
import allchive.server.domain.domains.report.domain.enums.ReportedType;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_report")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportObjectType reportObjectTypeType;

    private String reason;

    @Enumerated(EnumType.STRING)
    private ReportedType reportedType;

    private Long contentId;
    private Long archivingId;
    private Long userId; // 신고한 유저

    @Builder
    private Report(
            ReportObjectType reportObjectTypeType,
            String reason,
            ReportedType reportedType,
            Long contentId,
            Long archivingId,
            Long userId) {
        this.reportObjectTypeType = reportObjectTypeType;
        this.reason = reason;
        this.reportedType = reportedType;
        this.contentId = contentId;
        this.archivingId = archivingId;
        this.userId = userId;
    }

    public static Report of(
            ReportObjectType reportObjectTypeType,
            String reason,
            ReportedType reportedType,
            Long contentId,
            Long archivingId,
            Long userId) {
        return Report.builder()
                .reportObjectTypeType(reportObjectTypeType)
                .reason(reason)
                .reportedType(reportedType)
                .contentId(contentId)
                .archivingId(archivingId)
                .userId(userId)
                .build();
    }
}
