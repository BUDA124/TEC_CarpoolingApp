package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "PHONENUMBER")
public class PhoneNumberEntity implements Identifiable<Long> {

    public PhoneNumberEntity() {
    }

    public PhoneNumberEntity(String phoneNumber, PersonEntity idPerson, AuditLogEntity auditLogEntity) {
        this.phoneNumber = phoneNumber;
        this.person = idPerson;
        this.auditLog = auditLogEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHONENUMBER", nullable = false, length = 20)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPERSON", nullable = false)
    private PersonEntity person;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        PhoneNumberEntity that = (PhoneNumberEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "PhoneNumberEntity{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", personId=" + (person != null ? person.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}