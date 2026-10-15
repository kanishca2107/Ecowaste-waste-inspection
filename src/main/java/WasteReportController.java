package com.waste.waste_inspection;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class WasteReportController {

    private final WasteReportRepository repository;

    public WasteReportController(WasteReportRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<WasteReport> getAllReports() {
        return repository.findAll();
    }

    @PostMapping
    public WasteReport createReport(@RequestBody WasteReport report) {
        if (report.getLocation() == null
                || report.getDescription() == null
                || report.getLocation().trim().isEmpty()
                || report.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Location and description are required");
        }

        report.setLocation(report.getLocation().trim());
        report.setDescription(report.getDescription().trim());

        if (report.getSeverity() == null
                || !List.of("LOW", "MEDIUM", "HIGH")
                        .contains(report.getSeverity().toUpperCase())) {
            throw new IllegalArgumentException(
                    "Severity must be LOW, MEDIUM, or HIGH");
        }

        report.setSeverity(report.getSeverity().toUpperCase());

        if (report.getStatus() == null
                || report.getStatus().trim().isEmpty()) {
            report.setStatus("PENDING");
        }

        return repository.save(report);
    }

    @PutMapping("/{id}/status")
    public WasteReport updateReportStatus(
            @PathVariable Long id,
            @RequestBody StatusRequest request) {

        WasteReport report = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Report not found: " + id));

        if (request.getStatus() == null) {
            throw new IllegalArgumentException("Status is required");
        }

        String status = request.getStatus().trim().toUpperCase();

        if (!List.of("PENDING", "UNDER_REVIEW", "INSPECTED")
                .contains(status)) {
            throw new IllegalArgumentException(
                    "Status must be PENDING, UNDER_REVIEW, or INSPECTED");
        }

        report.setStatus(status);
        return repository.save(report);
    }

    public static class StatusRequest {
        private String status;

        public StatusRequest() {
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}