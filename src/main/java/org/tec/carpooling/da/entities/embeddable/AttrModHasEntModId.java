package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AttrModHasEntModId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDLOGBOOK")
    private Long idLogBook;

    @Column(name = "IDATTRIBUTEMODIFIED")
    private Long idAttributeModified;

    public AttrModHasEntModId() {}
    
    public AttrModHasEntModId(Long idLogBook, Long idAttributeModified) {
        this.idLogBook = idLogBook;
        this.idAttributeModified = idAttributeModified;
    }

    // Getters and Setters
    public Long getIdLogBook() { return idLogBook; }
    public void setIdLogBook(Long idLogBook) { this.idLogBook = idLogBook; }
    public Long getIdAttributeModified() { return idAttributeModified; }
    public void setIdAttributeModified(Long idAttributeModified) { this.idAttributeModified = idAttributeModified; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttrModHasEntModId that = (AttrModHasEntModId) o;
        return Objects.equals(idLogBook, that.idLogBook) &&
               Objects.equals(idAttributeModified, that.idAttributeModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogBook, idAttributeModified);
    }
}