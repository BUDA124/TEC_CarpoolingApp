package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PassengerQueryTripId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDUSER") // Corresponds to PersonalUser ID
    private Long idUser;

    @Column(name = "IDTRIP")
    private Long idTrip;

    public PassengerQueryTripId() {}

    public PassengerQueryTripId(Long idUser, Long idTrip) {
        this.idUser = idUser;
        this.idTrip = idTrip;
    }

    // Getters and Setters
    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }
    public Long getIdTrip() { return idTrip; }
    public void setIdTrip(Long idTrip) { this.idTrip = idTrip; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerQueryTripId that = (PassengerQueryTripId) o;
        return Objects.equals(idUser, that.idUser) &&
               Objects.equals(idTrip, that.idTrip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idTrip);
    }
}