package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import org.tec.carpooling.common.utils.HashingUtil;

import java.io.Serializable;
import java.util.Objects;

public class TripHasStopHasPaymentMethodId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDPAYMENTMETHOD") // Admin's Person ID
    private Long idPaymentMethod;

    @Column(name = "IDTRIP")
    private Long idTrip;

    @Column(name = "IDTRIP")
    private Long idStop;

    @Column(name = "IDAUDITLOG")
    private Long idAuditLog;

    public TripHasStopHasPaymentMethodId() {}

    public Long getIdStop() {
        return idStop;
    }

    public void setIdStop(Long idStop) {
        this.idStop = idStop;
    }

    public Long getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Long idTrip) {
        this.idTrip = idTrip;
    }

    public Long getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(Long idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public Long getIdAuditLog() {
        return idAuditLog;
    }

    public void setIdAuditLog(Long idAuditLog) {
        this.idAuditLog = idAuditLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripHasStopHasPaymentMethodId that = (TripHasStopHasPaymentMethodId) o;
        return Objects.equals(idPaymentMethod, that.idPaymentMethod) && Objects.equals(idTrip, that.idTrip) && Objects.equals(idStop, that.idStop);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(idPaymentMethod, idTrip, idStop);
    }

    @Override
    public String toString() {
        return "TripHasStopHasPaymentMethodId{" +
                "idPaymentMethod=" + idPaymentMethod +
                ", idTrip=" + idTrip +
                ", idStop=" + idStop +
                '}';
    }
}
