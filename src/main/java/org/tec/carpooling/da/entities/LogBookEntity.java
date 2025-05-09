package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime; // LOGTIME is TIMESTAMP
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "LOGBOOK")
@SequenceGenerator(name = "seq_logbook_gen", sequenceName = "SEQ_LOGBOOK", allocationSize = 1)
public class LogBookEntity implements Identifiable<Long> {

    public LogBookEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_logbook_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGDATE", nullable = false)
    private LocalDate logDate;

    @Column(name = "LOGTIME", nullable = false)
    private LocalDateTime logTime;

    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuditLogEntity getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogEntity auditLog) {
        this.auditLog = auditLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogBookEntity that = (LogBookEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "LogBookEntity{" +
                "id=" + id +
                ", logDate=" + logDate +
                ", logTime=" + logTime +
                ", description='" + description + '\'' +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}