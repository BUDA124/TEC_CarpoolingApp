package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "TRIP")
public class TripEntity implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MAXIMUNPASSENGERS", nullable = false)
    private Integer maximumPassengers;

    @Column(name = "DEPARTUREDATETIME", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "DURATIONESTIMATE", nullable = false)
    private Integer durationEstimate;

    @Column(name = "ARRIVALDATETIME", nullable = false)
    private LocalDateTime arrivalDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDDRIVER", referencedColumnName = "IDPERSON", nullable = false)
    private DriverEntity driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPRICESTATUS") // Nullable
    private PriceStatusEntity priceStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDVEHICLE", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public TripEntity() {
    }

    public TripEntity(int maximumPassengers, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int durationEstimate, DriverEntity driverEntity,
                      VehicleEntity vehicleEntity, PriceStatusEntity priceStatus, AuditLogEntity auditLogEntity) {
        this.maximumPassengers = maximumPassengers;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.durationEstimate = durationEstimate;
        this.driver = driverEntity;
        this.vehicle = vehicleEntity;
        this.priceStatus = priceStatus;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaximumPassengers() {
        return maximumPassengers;
    }

    public void setMaximumPassengers(Integer maximumPassengers) {
        this.maximumPassengers = maximumPassengers;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Integer getDurationEstimate() {
        return durationEstimate;
    }

    public void setDurationEstimate(Integer durationEstimate) {
        this.durationEstimate = durationEstimate;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    public PriceStatusEntity getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(PriceStatusEntity priceStatus) {
        this.priceStatus = priceStatus;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public AuditLogEntity getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogEntity auditLog) {
        this.auditLog = auditLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripEntity that = (TripEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "TripEntity{" +
                "id=" + id +
                ", maximumPassengers=" + maximumPassengers +
                ", departureDateTime=" + departureDateTime +
                ", durationEstimate=" + durationEstimate +
                ", arrivalDateTime=" + arrivalDateTime +
                ", driverId=" + (driver != null ? driver.getId() : null) +
                ", priceStatusId=" + (priceStatus != null ? priceStatus.getId() : null) +
                ", vehicleId=" + (vehicle != null ? vehicle.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}