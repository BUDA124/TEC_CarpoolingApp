package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "INSTITUTIONALEMAIL")
@SequenceGenerator(name = "seq_institutionalemail_gen", sequenceName = "SEQ_INSTITUTIONALEMAIL", allocationSize = 1)
public class InstitutionalEmailEntity implements Identifiable<Long> {

    public InstitutionalEmailEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_institutionalemail_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAILADDRESS", nullable = false, length = 255)
    private String emailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDUSER", nullable = false) // References PERSONALUSER(ID)
    private PersonalUserEntity personalUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public InstitutionalEmailEntity(String emailAddress, PersonalUserEntity user, AuditLogEntity auditLogEntity) {
        this.emailAddress = emailAddress;
        this.personalUser = user;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public PersonalUserEntity getPersonalUser() {
        return personalUser;
    }

    public void setPersonalUser(PersonalUserEntity personalUser) {
        this.personalUser = personalUser;
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
        InstitutionalEmailEntity that = (InstitutionalEmailEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "InstitutionalEmailEntity{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                ", personalUserId=" + (personalUser != null ? personalUser.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}