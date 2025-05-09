package org.tec.carpooling.da.entities.embeddable; // Put TripHasStopId here

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TripHasStopId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDTRIP")
    private Long idTrip;

    @Column(name = "IDSTOP")
    private Long idStop;

    public TripHasStopId() {}
    public TripHasStopId(Long idTrip, Long idStop) {
        this.idTrip = idTrip;
        this.idStop = idStop;
    }
    public Long getIdTrip() { return idTrip; }
    public void setIdTrip(Long idTrip) { this.idTrip = idTrip; }
    public Long getIdStop() { return idStop; }
    public void setIdStop(Long idStop) { this.idStop = idStop; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripHasStopId that = (TripHasStopId) o;
        return Objects.equals(idTrip, that.idTrip) && Objects.equals(idStop, that.idStop);
    }
    @Override
    public int hashCode() { return Objects.hash(idTrip, idStop); }
}