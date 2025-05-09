package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "INSTITUTION", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"EMAILDOMAIN"}, name = "UK_INSTITUTION_EMAILDOMAIN")
})
@SequenceGenerator(name = "seq_institution_gen", sequenceName = "SEQ_INSTITUTION", allocationSize = 1)
public class InstitutionEntity implements Identifiable<Long> {

    public InstitutionEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_institution_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAILDOMAIN", nullable = false, length = 100, unique = true)
    private String emailDomain;

    @Column(name = "INSTITUTIONNAME", nullable = false, length = 255)
    private String institutionName;

    @Column(name = "WEBSITE", length = 512)
    private String website;

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

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
        InstitutionEntity that = (InstitutionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "InstitutionEntity{" +
                "id=" + id +
                ", emailDomain='" + emailDomain + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", website='" + website + '\'' +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}