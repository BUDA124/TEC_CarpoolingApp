package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TripHasTripStatusId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDTRIP")
    private Long idTrip;

    @Column(name = "IDTRIPSTATUS")
    private Long idTripStatus;
    
    public TripHasTripStatusId() {}

    public TripHasTripStatusId(Long idTrip, Long idTripStatus) {
        this.idTrip = idTrip;
        this.idTripStatus = idTripStatus;
    }

    // Getters and Setters
    public Long getIdTrip() { return idTrip; }
    public void setIdTrip(Long idTrip) { this.idTrip = idTrip; }
    public Long getIdTripStatus() { return idTripStatus; }
    public void setIdTripStatus(Long idTripStatus) { this.idTripStatus = idTripStatus; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripHasTripStatusId that = (TripHasTripStatusId) o;
        return Objects.equals(idTrip, that.idTrip) &&
               Objects.equals(idTripStatus, that.idTripStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrip, idTripStatus);
    }
}