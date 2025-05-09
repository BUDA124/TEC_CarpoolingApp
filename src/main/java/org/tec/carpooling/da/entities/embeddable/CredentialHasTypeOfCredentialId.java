package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CredentialHasTypeOfCredentialId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDCREDENTIAL")
    private Long idCredential;

    @Column(name = "IDTYPEOFCREDENTIAL")
    private Long idTypeOfCredential;
    
    public CredentialHasTypeOfCredentialId() {}

    public CredentialHasTypeOfCredentialId(Long idCredential, Long idTypeOfCredential) {
        this.idCredential = idCredential;
        this.idTypeOfCredential = idTypeOfCredential;
    }

    // Getters and Setters
    public Long getIdCredential() { return idCredential; }
    public void setIdCredential(Long idCredential) { this.idCredential = idCredential; }
    public Long getIdTypeOfCredential() { return idTypeOfCredential; }
    public void setIdTypeOfCredential(Long idTypeOfCredential) { this.idTypeOfCredential = idTypeOfCredential; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialHasTypeOfCredentialId that = (CredentialHasTypeOfCredentialId) o;
        return Objects.equals(idCredential, that.idCredential) &&
               Objects.equals(idTypeOfCredential, that.idTypeOfCredential);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCredential, idTypeOfCredential);
    }
}