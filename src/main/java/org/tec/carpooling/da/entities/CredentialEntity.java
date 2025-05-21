package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "CREDENTIAL")
@SequenceGenerator(name = "seq_credential_gen", sequenceName = "SEQ_CREDENTIAL", allocationSize = 1)
public class CredentialEntity implements Identifiable<Long> {

    public CredentialEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_credential_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "ISACTIVE", nullable = false, columnDefinition = "NUMBER(1)") // Mapped as Integer due to 0/1 values
    private Integer isActive;

    @Column(name = "NUMBEROFCREDENTIAL", nullable = false, length = 255)
    private String numberOfCredential;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPERSON", nullable = false)
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public CredentialEntity(int isActive, String numberOfCredential, PersonEntity person, AuditLogEntity auditLogEntity) {
        this.isActive = isActive;
        this.numberOfCredential = numberOfCredential;
        this.person = person;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getNumberOfCredential() {
        return numberOfCredential;
    }

    public void setNumberOfCredential(String numberOfCredential) {
        this.numberOfCredential = numberOfCredential;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
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
        CredentialEntity that = (CredentialEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "CredentialEntity{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", numberOfCredential='" + numberOfCredential + '\'' +
                ", personId=" + (person != null ? person.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}