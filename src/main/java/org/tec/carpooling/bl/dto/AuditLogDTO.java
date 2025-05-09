package org.tec.carpooling.bl.dto;

import java.time.LocalDateTime;

public class AuditLogDTO {
    private Long id;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String updatedBy;

    public AuditLogDTO() {
    }

    public AuditLogDTO(Long id, LocalDateTime creationDate, String createdBy, LocalDateTime lastUpdateDate, String updatedBy) {
        this.id = id;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.updatedBy = updatedBy;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}