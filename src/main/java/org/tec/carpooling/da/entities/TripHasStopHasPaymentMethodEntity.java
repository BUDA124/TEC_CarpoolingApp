package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.TripHasStopHasPaymentMethodId;
import java.util.Objects;

@Entity
@Table(name = "TRIPHASSTOPHASPAYMENTMETHOD")
public class TripHasStopHasPaymentMethodEntity implements Identifiable<TripHasStopHasPaymentMethodId> {

    @EmbeddedId
    private TripHasStopHasPaymentMethodId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPaymentMethod")
    @JoinColumn(name = "IDPAYMENTMETHOD", referencedColumnName = "ID", insertable = false, updatable = false)
    private PaymentMethodEntity paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTrip")
    @JoinColumn(name = "IDTRIP", referencedColumnName = "ID", insertable = false, updatable = false)
    private TripEntity trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idStop")
    @JoinColumn(name = "IDSTOP", referencedColumnName = "ID", insertable = false, updatable = false)
    private StopEntity stop;

    public TripHasStopHasPaymentMethodEntity() {}

    // Getters and Setters
    public TripHasStopHasPaymentMethodId getId() { return id; }
    public void setId(TripHasStopHasPaymentMethodId id) { this.id = id; }
    public PaymentMethodEntity getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethodEntity paymentMethod) { this.paymentMethod = paymentMethod; }
    public TripEntity getTrip() { return trip; }
    public void setTrip(TripEntity trip) { this.trip = trip; }
    public StopEntity getStop() { return stop; }
    public void setStop(StopEntity stop) { this.stop = stop; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripHasStopHasPaymentMethodEntity that = (TripHasStopHasPaymentMethodEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "TripHasStopHasPaymentMethodEntity{" +
                "id=" + id +
                ", paymentMethodId=" + (paymentMethod != null ? paymentMethod.getId() : null) +
                ", tripId=" + (trip != null ? trip.getId() : null) +
                ", stopId=" + (stop != null ? stop.getId() : null) +
                '}';
    }
}