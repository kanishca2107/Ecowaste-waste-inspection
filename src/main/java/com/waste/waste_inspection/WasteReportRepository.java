package com.waste.waste_inspection;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WasteReportRepository
        extends JpaRepository<WasteReport, Long> {

    List<WasteReport> findBySeverityIgnoreCase(String severity);

    List<WasteReport> findByStatusIgnoreCase(String status);
}