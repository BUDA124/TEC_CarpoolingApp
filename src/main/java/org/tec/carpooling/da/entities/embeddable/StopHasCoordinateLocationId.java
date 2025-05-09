package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StopHasCoordinateLocationId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDSTOP")
    private Long idStop;

    @Column(name = "IDCOORDINATELOCATION")
    private Long idCoordinateLocation;

    public StopHasCoordinateLocationId() {}
    
    public StopHasCoordinateLocationId(Long idStop, Long idCoordinateLocation) {
        this.idStop = idStop;
        this.idCoordinateLocation = idCoordinateLocation;
    }

    // Getters and Setters
    public Long getIdStop() { return idStop; }
    public void setIdStop(Long idStop) { this.idStop = idStop; }
    public Long getIdCoordinateLocation() { return idCoordinateLocation; }
    public void setIdCoordinateLocation(Long idCoordinateLocation) { this.idCoordinateLocation = idCoordinateLocation; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopHasCoordinateLocationId that = (StopHasCoordinateLocationId) o;
        return Objects.equals(idStop, that.idStop) &&
               Objects.equals(idCoordinateLocation, that.idCoordinateLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStop, idCoordinateLocation);
    }
}