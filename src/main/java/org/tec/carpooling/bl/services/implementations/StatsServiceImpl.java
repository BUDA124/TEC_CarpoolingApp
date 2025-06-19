package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.dto.AdminDashboardStatsDTO;
import org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO;
import org.tec.carpooling.bl.services.StatsService;
import org.tec.carpooling.da.repositories.DriverRepository;
import org.tec.carpooling.da.repositories.PassengerRepository;
import org.tec.carpooling.da.repositories.PersonRepository;
import org.tec.carpooling.da.repositories.TripRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<ChartDataDTO> getUsersCountByAgeGroup(String genderName) {
        return personRepository.countUsersByAgeGroup(genderName);
    }

    @Override
    public List<ChartDataDTO> getDriversCountByGender(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        return driverRepository.countDriversByGenderInDateRange(startDateTime, endDateTime);
    }

    @Override
    public List<ChartDataDTO> getPassengersCountByGender(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        return passengerRepository.countPassengersByGenderInDateRange(startDateTime, endDateTime);
    }

    @Override
    public List<ChartDataDTO> getTripCountByHour() {
        return tripRepository.countTripsByHour();
    }

    @Override
    public AdminDashboardStatsDTO getAdminDashboardStats() {
        // Obtener datos de grupos de edad
        List<ChartDataDTO> ageData = getUsersCountByAgeGroup(null);

        // Obtener datos de género para el año actual
        LocalDate now = LocalDate.now();
        LocalDate startOfYear = now.withDayOfYear(1);
        List<ChartDataDTO> driverGenderData = getDriversCountByGender(startOfYear, now);
        List<ChartDataDTO> passengerGenderData = getPassengersCountByGender(startOfYear, now);

        // Obtener datos de tráfico por hora
        List<ChartDataDTO> trafficData = getTripCountByHour();

        // Empaquetar todo en el DTO y devolverlo
        return new AdminDashboardStatsDTO(ageData, driverGenderData, passengerGenderData, trafficData);
    }
}