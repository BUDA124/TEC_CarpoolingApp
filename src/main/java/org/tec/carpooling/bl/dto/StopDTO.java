package org.tec.carpooling.bl.dto;

public class StopDTO {
    private Long id;
    private String address;
    private DistrictDTO district;
    private TripDTO startTrip; // Represents IDSTARTRIP FK to TRIP.ID (Trip for which this is start stop)
    private TripDTO endTrip;   // Represents IDENDTRIP FK to TRIP.ID (Trip for which this is end stop)
    private AuditLogDTO auditLog;

    public StopDTO() {
    }

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

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

    public TripDTO getStartTrip() {
        return startTrip;
    }

    public void setStartTrip(TripDTO startTrip) {
        this.startTrip = startTrip;
    }

    public TripDTO getEndTrip() {
        return endTrip;
    }

    public void setEndTrip(TripDTO endTrip) {
        this.endTrip = endTrip;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}