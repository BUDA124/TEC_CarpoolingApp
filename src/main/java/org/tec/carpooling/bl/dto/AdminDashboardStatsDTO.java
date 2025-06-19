package org.tec.carpooling.bl.dto;

import org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO;

import java.util.List;

/**
 * DTO que encapsula todos los datos necesarios para el panel de estadísticas del administrador.
 */
public class AdminDashboardStatsDTO {

    private final List<ChartDataDTO> ageGroupData;
    private final List<ChartDataDTO> driverGenderData;
    private final List<ChartDataDTO> passengerGenderData;
    private final List<ChartDataDTO> peakTrafficData;

    public AdminDashboardStatsDTO(List<ChartDataDTO> ageGroupData, List<ChartDataDTO> driverGenderData, List<ChartDataDTO> passengerGenderData, List<ChartDataDTO> peakTrafficData) {
        this.ageGroupData = ageGroupData;
        this.driverGenderData = driverGenderData;
        this.passengerGenderData = passengerGenderData;
        this.peakTrafficData = peakTrafficData;
    }

    // --- Getters ---

    public List<ChartDataDTO> getAgeGroupData() {
        return ageGroupData;
    }

    public List<ChartDataDTO> getDriverGenderData() {
        return driverGenderData;
    }

    public List<ChartDataDTO> getPassengerGenderData() {
        return passengerGenderData;
    }

    public List<ChartDataDTO> getPeakTrafficData() {
        return peakTrafficData;
    }
}