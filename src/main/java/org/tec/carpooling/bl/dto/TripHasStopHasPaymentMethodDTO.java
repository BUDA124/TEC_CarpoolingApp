package org.tec.carpooling.bl.dto;

public class TripHasStopHasPaymentMethodDTO {
    private PaymentMethodDTO paymentMethod;
    private TripDTO trip;
    private StopDTO stop;
    private AuditLogDTO auditLog;

    public TripHasStopHasPaymentMethodDTO() {
    }

    // Getters and Setters
    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public StopDTO getStop() {
        return stop;
    }

    public void setStop(StopDTO stop) {
        this.stop = stop;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}