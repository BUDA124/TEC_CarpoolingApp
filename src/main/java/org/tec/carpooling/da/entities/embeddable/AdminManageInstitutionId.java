package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AdminManageInstitutionId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDPERSON") // Admin's Person ID
    private Long idPerson;

    @Column(name = "IDINSTITUTION")
    private Long idInstitution;

    public AdminManageInstitutionId() {}

    public AdminManageInstitutionId(Long idPerson, Long idInstitution) {
        this.idPerson = idPerson;
        this.idInstitution = idInstitution;
    }

    // Getters and Setters
    public Long getIdPerson() { return idPerson; }
    public void setIdPerson(Long idPerson) { this.idPerson = idPerson; }
    public Long getIdInstitution() { return idInstitution; }
    public void setIdInstitution(Long idInstitution) { this.idInstitution = idInstitution; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminManageInstitutionId that = (AdminManageInstitutionId) o;
        return Objects.equals(idPerson, that.idPerson) &&
               Objects.equals(idInstitution, that.idInstitution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerson, idInstitution);
    }
}