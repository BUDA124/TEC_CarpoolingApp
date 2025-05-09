package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LogBookHasEntityModifiedId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDLOGBOOK")
    private Long idLogBook;

    @Column(name = "IDENTITYMODIFIED")
    private Long idEntityModified;
    
    public LogBookHasEntityModifiedId() {}

    public LogBookHasEntityModifiedId(Long idLogBook, Long idEntityModified) {
        this.idLogBook = idLogBook;
        this.idEntityModified = idEntityModified;
    }

    // Getters and Setters
    public Long getIdLogBook() { return idLogBook; }
    public void setIdLogBook(Long idLogBook) { this.idLogBook = idLogBook; }
    public Long getIdEntityModified() { return idEntityModified; }
    public void setIdEntityModified(Long idEntityModified) { this.idEntityModified = idEntityModified; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogBookHasEntityModifiedId that = (LogBookHasEntityModifiedId) o;
        return Objects.equals(idLogBook, that.idLogBook) &&
               Objects.equals(idEntityModified, that.idEntityModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogBook, idEntityModified);
    }
}