package org.tec.carpooling.bl.dto;

public class VehicleDTO {
    private Long id;
    private Boolean isVerified;
    private String brand;
    private byte[] carPhoto;
    private String plateNumber;
    private String carModel;
    private Integer seatQuantity;
    private DriverDTO driver;
    private AuditLogDTO auditLog;

    public VehicleDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public byte[] getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(byte[] carPhoto) {
        this.carPhoto = carPhoto;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getSeatQuantity() {
        return seatQuantity;
    }

    public void setSeatQuantity(Integer seatQuantity) {
        this.seatQuantity = seatQuantity;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}