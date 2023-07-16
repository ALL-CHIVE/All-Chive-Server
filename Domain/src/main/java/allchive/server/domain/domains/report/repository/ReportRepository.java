package allchive.server.domain.domains.report.repository;


import allchive.server.domain.domains.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportCustomRepository {}
