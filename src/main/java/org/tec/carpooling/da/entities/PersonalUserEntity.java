package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "PERSONALUSER", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USERNAME"}, name = "UK_USER_USERNAME")
})
@SequenceGenerator(name = "seq_personaluser_gen", sequenceName = "SEQ_PERSONALUSER", allocationSize = 1)
public class
PersonalUserEntity implements Identifiable<Long> {

    public PersonalUserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_personaluser_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @Column(name = "USERNAME", nullable = false, length = 100, unique = true)
    private String username;

    @Column(name = "REGISTRATIONDATE", nullable = false)
    private LocalDate registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPERSON", nullable = false)
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDUSERSTATUS" /*, nullable = false si un usuario SIEMPRE debe tener un estado */)
    private UserStatusEntity userStatus; // Esta será la FK a la tabla USERSTATUS

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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

    public UserStatusEntity getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusEntity userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalUserEntity that = (PersonalUserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "PersonalUserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                // ", password='[PROTECTED]'" + // Avoid logging password
                ", registrationDate=" + registrationDate +
                ", personId=" + (person != null ? person.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}