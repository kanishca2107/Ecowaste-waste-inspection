package com.waste.waste_inspection;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "waste_reports")
public class WasteReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(length = 20, nullable = false)
    private String severity = "LOW";

    @Column(length = 30, nullable = false)
    private String status = "PENDING";

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public WasteReport() {
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}