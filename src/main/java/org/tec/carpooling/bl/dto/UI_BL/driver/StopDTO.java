package org.tec.carpooling.bl.dto.UI_BL.driver;

import org.tec.carpooling.da.entities.AuditLogEntity;
import org.tec.carpooling.da.entities.DistrictEntity;

import java.time.LocalTime;

public class StopDTO {

    private String address;
    private DistrictEntity district;
    private LocalTime leavesAt;
    private LocalTime arrivesAt;
    private double cost;
    private String paymentMethod;
    private DistrictEntity startStop;
    private DistrictEntity endStop;
    private AuditLogEntity auditLog;

    public StopDTO(String address, DistrictEntity district, LocalTime leavesAt,
                   LocalTime arrivesAt, double cost, String paymentMethod, DistrictEntity startStop, DistrictEntity endStop, AuditLogEntity auditLog) {
        this.address = address;
        this.district = district;
        this.leavesAt = leavesAt;
        this.arrivesAt = arrivesAt;
        this.cost = cost;
        this.paymentMethod = paymentMethod;
        this.startStop = startStop;
        this.endStop = endStop;
        this.auditLog = auditLog;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public LocalTime getLeavesAt() {
        return leavesAt;
    }

    public void setLeavesAt(LocalTime leavesAt) {
        this.leavesAt = leavesAt;
    }

    public LocalTime getArrivesAt() {
        return arrivesAt;
    }

    public void setArrivesAt(LocalTime arrivesAt) {
        this.arrivesAt = arrivesAt;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public DistrictEntity getStartStop() {
        return startStop;
    }

    public void setStartStop(DistrictEntity startStop) {
        this.startStop = startStop;
    }

    public DistrictEntity getEndStop() {
        return endStop;
    }

    public void setEndStop(DistrictEntity endStop) {
        this.endStop = endStop;
    }

    public AuditLogEntity getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogEntity auditLog) {
        this.auditLog = auditLog;
    }

    @Override
    public String toString() {
        return "StopDTO{" +
                "address='" + address + '\'' +
                ", leavesAt=" + leavesAt +
                ", arrivesAt=" + arrivesAt +
                ", cost=" + cost +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}