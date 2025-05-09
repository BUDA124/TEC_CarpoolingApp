package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "STOP")
@SequenceGenerator(name = "seq_stop_gen", sequenceName = "SEQ_STOP", allocationSize = 1)
public class StopEntity implements Identifiable<Long> {

    public StopEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_stop_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "ADDRESS", nullable = false, length = 255)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDDISTRICT", nullable = false)
    private DistrictEntity district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDSTARTRIP") // Nullable
    private TripEntity startTrip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDENDTRIP") // Nullable
    private TripEntity endTrip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TripEntity getStartTrip() {
        return startTrip;
    }

    public void setStartTrip(TripEntity startTrip) {
        this.startTrip = startTrip;
    }

    public TripEntity getEndTrip() {
        return endTrip;
    }

    public void setEndTrip(TripEntity endTrip) {
        this.endTrip = endTrip;
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
        StopEntity that = (StopEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "StopEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", districtId=" + (district != null ? district.getId() : null) +
                ", startTripId=" + (startTrip != null ? startTrip.getId() : null) +
                ", endTripId=" + (endTrip != null ? endTrip.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}