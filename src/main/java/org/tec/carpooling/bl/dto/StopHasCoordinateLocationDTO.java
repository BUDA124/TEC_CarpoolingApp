package org.tec.carpooling.bl.dto;

public class StopHasCoordinateLocationDTO {
    private StopDTO stop;
    private CoordinateLocationDTO coordinateLocation;
    private AuditLogDTO auditLog;

    public StopHasCoordinateLocationDTO() {
    }

    // Getters and Setters
    public StopDTO getStop() {
        return stop;
    }

    public void setStop(StopDTO stop) {
        this.stop = stop;
    }

    public CoordinateLocationDTO getCoordinateLocation() {
        return coordinateLocation;
    }

    public void setCoordinateLocation(CoordinateLocationDTO coordinateLocation) {
        this.coordinateLocation = coordinateLocation;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}