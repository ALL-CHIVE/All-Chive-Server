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
    private Long reportedUserId; // 신고 당한 유저 id

    @Builder
    private Report(
            ReportObjectType reportObjectTypeType,
            String reason,
            ReportedType reportedType,
            Long contentId,
            Long archivingId,
            Long userId,
            Long reportedUserId) {
        this.reportObjectTypeType = reportObjectTypeType;
        this.reason = reason;
        this.reportedType = reportedType;
        this.contentId = contentId;
        this.archivingId = archivingId;
        this.userId = userId;
        this.reportedUserId = reportedUserId;
    }

    public static Report of(
            ReportObjectType reportObjectTypeType,
            String reason,
            ReportedType reportedType,
            Long contentId,
            Long archivingId,
            Long userId,
            Long reportedUserId) {
        return Report.builder()
                .reportObjectTypeType(reportObjectTypeType)
                .reason(reason)
                .reportedType(reportedType)
                .contentId(contentId)
                .archivingId(archivingId)
                .userId(userId)
                .reportedUserId(reportedUserId)
                .build();
    }
}
