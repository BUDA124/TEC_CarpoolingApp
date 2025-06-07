package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.sql.Blob;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "VEHICLE")
public class VehicleEntity implements Identifiable<Long> {

    public VehicleEntity() {
    }

    public VehicleEntity(String brand, String carModel, String plateNumber, int seatQuantity, int isVerified, DriverEntity driver, AuditLogEntity auditLogEntity) {
        this.brand = brand;
        this.carModel = carModel;
        this.plateNumber = plateNumber;
        this.seatQuantity = seatQuantity;
        this.isVerified = isVerified;
        this.driver = driver;
        this.auditLog = auditLogEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ISVERIFIED", nullable = false, columnDefinition = "TINYINT(1)") // Mapped as Integer
    private Integer isVerified;

    @Column(name = "BRAND", nullable = false, length = 100)
    private String brand;

    @Lob
    @Column(name = "CARPHOTO")
    private Blob carPhoto;

    @Column(name = "PLATENUMBER", nullable = false, length = 20, unique = true)
    private String plateNumber;

    @Column(name = "CARMODEL", nullable = false, length = 100)
    private String carModel;

    @Column(name = "SEATQUANTITY", nullable = false)
    private Integer seatQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDDRIVER", referencedColumnName = "IDPERSON", nullable = false)
    private DriverEntity driver;

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

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Blob getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(Blob carPhoto) {
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

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
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
        VehicleEntity that = (VehicleEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id=" + id +
                ", isVerified=" + isVerified +
                ", brand='" + brand + '\'' +
                // ", carPhoto=" + (carPhoto != null ? "Blob_Exists" : "null") +
                ", plateNumber='" + plateNumber + '\'' +
                ", carModel='" + carModel + '\'' +
                ", seatQuantity=" + seatQuantity +
                ", driverId=" + (driver != null ? driver.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}