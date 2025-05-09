package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "USERSTATUS")
@SequenceGenerator(name = "seq_userstatus_gen", sequenceName = "SEQ_USERSTATUS", allocationSize = 1)
public class UserStatusEntity implements Identifiable<Long> {

    public UserStatusEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_userstatus_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATUS", length = 50)
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        UserStatusEntity that = (UserStatusEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "UserStatusEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}