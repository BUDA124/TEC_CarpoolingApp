package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "CONTACTPHONENUMBER")
@SequenceGenerator(name = "seq_contactphonenumber_gen", sequenceName = "SEQ_CONTACTPHONENUMBER", allocationSize = 1)
public class ContactPhoneNumberEntity implements Identifiable<Long> {

    public ContactPhoneNumberEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contactphonenumber_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHONENUMBER", nullable = false, length = 20)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDINSTITUTION", nullable = false)
    private InstitutionEntity institution;

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

    public InstitutionEntity getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionEntity institution) {
        this.institution = institution;
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
        ContactPhoneNumberEntity that = (ContactPhoneNumberEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "ContactPhoneNumberEntity{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", institutionId=" + (institution != null ? institution.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}