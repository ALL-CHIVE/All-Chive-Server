package allchive.server.domain.domains.report.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.report.domain.enums.ReportType;
import allchive.server.domain.domains.report.domain.enums.ReportedType;
import javax.persistence.*;
import lombok.AccessLevel;
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
    private ReportType reportType;

    private String reason;

    @Enumerated(EnumType.STRING)
    private ReportedType reportedType;

    private Long contentId;
    private Long archivingId;
    private Long userId;
}
