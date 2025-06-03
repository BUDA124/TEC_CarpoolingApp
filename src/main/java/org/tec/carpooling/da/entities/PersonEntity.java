package org.tec.carpooling.da.entities;

import java.sql.Blob;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "PERSON")
@SequenceGenerator(name = "seq_person_gen", sequenceName = "SEQ_PERSON", allocationSize = 1)
public class PersonEntity implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRSTNAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "SECONDNAME", length = 50)
    private String secondName;

    @Column(name = "FIRSTSURNAME", nullable = false, length = 50)
    private String firstSurname;

    @Column(name = "SECONDSURNAME", length = 50)
    private String secondSurname;

    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDate birthdate;

    @Column(name = "NATIONALITY", nullable = false, length = 50)
    private String nationality;

    @Lob()
    @Column(name = "PROFILEPICTURE")
    private Blob profilePicture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDINSTITUTION", nullable = false)
    private InstitutionEntity idInstitution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity idAuditLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDGENDER", nullable = false)
    private GenderEntity idGender;

    public PersonEntity() {
    }

    public PersonEntity(String firstName, String secondName, String firstSurname, Object profilePicture,
                        LocalDate birthdate, String nationality, InstitutionEntity institution, GenderEntity gender, AuditLogEntity auditLogEntity) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstSurname = firstSurname;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.profilePicture = null;
        this.idInstitution = institution;
        this.idGender = gender;
        this.idAuditLog = auditLogEntity;
    }

    public GenderEntity getIdGender() {
        return idGender;
    }

    public void setIdGender(GenderEntity idGender) {
        this.idGender = idGender;
    }

    public AuditLogEntity getIdAuditLog() {
        return idAuditLog;
    }

    public void setIdAuditLog(AuditLogEntity auditLog) {
        this.idAuditLog = auditLog;
    }

    public InstitutionEntity getIdInstitution() {
        return idInstitution;
    }

    public void setIdInstitution(InstitutionEntity idInstitution) {
        this.idInstitution = idInstitution;
    }

    public Blob getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Blob profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) &&
                Objects.equals(firstSurname, that.firstSurname) && Objects.equals(secondSurname, that.secondSurname) &&
                Objects.equals(birthdate, that.birthdate) && Objects.equals(nationality, that.nationality) &&
                Objects.equals(profilePicture, that.profilePicture) && Objects.equals(idInstitution, that.idInstitution) &&
                Objects.equals(idAuditLog, that.idAuditLog) && Objects.equals(idGender, that.idGender);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", birthdate=" + birthdate +
                ", nationality='" + nationality + '\'' +
                ", profilePicture=" + profilePicture +
                ", idInstitution=" + idInstitution +
                ", auditLog=" + idAuditLog +
                ", idGender=" + idGender +
                '}';
    }
}