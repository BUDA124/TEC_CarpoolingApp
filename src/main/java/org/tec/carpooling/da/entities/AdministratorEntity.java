package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "ADMINISTRATOR")
// No SequenceGenerator, PK is derived from PersonEntity
public class AdministratorEntity implements Identifiable<Long> {

    public AdministratorEntity() {
    }

    @Id
    @Column(name = "IDPERSON")
    private Long idPerson;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // This maps the idPerson field to the id of PersonEntity for the PK
    @JoinColumn(name = "IDPERSON", referencedColumnName = "ID")
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public AdministratorEntity(PersonEntity person, AuditLogEntity auditLogEntity) {
        this.person = person;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public Long getId() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
        if (person != null) {
            this.idPerson = person.getId();
        }
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
        AdministratorEntity that = (AdministratorEntity) o;
        return Objects.equals(idPerson, that.idPerson);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(idPerson);
    }

    @Override
    public String toString() {
        return "AdministratorEntity{" +
                "idPerson=" + idPerson +
                ", personId=" + (person != null ? person.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}