package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.CredentialHasTypeOfCredentialId;
import java.util.Objects;

@Entity
@Table(name = "CREDENTIALHASTYPEOFCREDENTIAL")
public class CredentialHasTypeOfCredentialEntity implements Identifiable<CredentialHasTypeOfCredentialId> {

    @EmbeddedId
    private CredentialHasTypeOfCredentialId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCredential") // Corresponds to the field name in CredentialHasTypeOfCredentialId
    @JoinColumn(name = "IDCREDENTIAL", referencedColumnName = "ID", insertable = false, updatable = false)
    private CredentialEntity credential;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTypeOfCredential") // Corresponds to the field name in CredentialHasTypeOfCredentialId
    @JoinColumn(name = "IDTYPEOFCREDENTIAL", referencedColumnName = "ID", insertable = false, updatable = false)
    private TypeOfCredentialEntity typeOfCredential;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public CredentialHasTypeOfCredentialEntity() {}

    // Getters and Setters
    public CredentialHasTypeOfCredentialId getId() { return id; }
    public void setId(CredentialHasTypeOfCredentialId id) { this.id = id; }
    public CredentialEntity getCredential() { return credential; }
    public void setCredential(CredentialEntity credential) { this.credential = credential; }
    public TypeOfCredentialEntity getTypeOfCredential() { return typeOfCredential; }
    public void setTypeOfCredential(TypeOfCredentialEntity typeOfCredential) { this.typeOfCredential = typeOfCredential; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialHasTypeOfCredentialEntity that = (CredentialHasTypeOfCredentialEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // id itself implements hashCode correctly
    }

    @Override
    public String toString() {
        return "CredentialHasTypeOfCredentialEntity{" +
                "id=" + id +
                ", credentialId=" + (credential != null ? credential.getId() : null) +
                ", typeOfCredentialId=" + (typeOfCredential != null ? typeOfCredential.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}