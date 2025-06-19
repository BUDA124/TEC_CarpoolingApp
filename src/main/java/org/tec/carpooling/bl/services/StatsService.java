package org.tec.carpooling.bl.services;

import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.dto.AdminDashboardStatsDTO;
import org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO;

import java.time.LocalDate;
import java.util.List;

@Service
public interface StatsService {

    /**
     * Orquesta la obtención de todos los datos estadísticos para el panel del administrador.
     * Esta es la lógica que antes estaba en el controlador.
     * @return Un DTO que contiene todos los datos necesarios para los gráficos del panel.
     */
    AdminDashboardStatsDTO getAdminDashboardStats();

    /**
     * Obtiene el recuento de usuarios agrupados por rangos de edad.
     * @param genderName El nombre del género para filtrar, o null para todos.
     * @return Una lista de DTOs con la etiqueta del rango de edad y la cantidad.
     */
    List<ChartDataDTO> getUsersCountByAgeGroup(String genderName);

    /**
     * Obtiene el recuento de conductores por género en un rango de fechas.
     * @param startDate Fecha de inicio del filtro.
     * @param endDate Fecha de fin del filtro.
     * @return Una lista de DTOs con la etiqueta del género y la cantidad.
     */
    List<ChartDataDTO> getDriversCountByGender(LocalDate startDate, LocalDate endDate);

    /**
     * Obtiene el recuento de pasajeros por género en un rango de fechas.
     * @param startDate Fecha de inicio del filtro.
     * @param endDate Fecha de fin del filtro.
     * @return Una lista de DTOs con la etiqueta del género y la cantidad.
     */
    List<ChartDataDTO> getPassengersCountByGender(LocalDate startDate, LocalDate endDate);

    /**
     * Obtiene el recuento de viajes por hora del día para analizar el tráfico.
     * @return Una lista de DTOs con la hora como etiqueta y la cantidad de viajes.
     */
    List<ChartDataDTO> getTripCountByHour();
}