package allchive.server.api.report.model.mapper;


import allchive.server.api.report.model.dto.request.CreateReportRequest;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.report.domain.Report;
import allchive.server.domain.domains.report.domain.enums.ReportObjectType;

@Mapper
public class ReportMapper {
    public Report toEntity(CreateReportRequest request, ReportObjectType type, Long userId) {
        Report report = null;
        switch (type) {
            case CONTENT -> report =
                    Report.of(
                            type,
                            request.getReason(),
                            request.getReportedType(),
                            request.getId(),
                            null,
                            userId);
            case ARCHIVING -> report =
                    Report.of(
                            type,
                            request.getReason(),
                            request.getReportedType(),
                            null,
                            request.getId(),
                            userId);
        }
        return report;
    }
}
