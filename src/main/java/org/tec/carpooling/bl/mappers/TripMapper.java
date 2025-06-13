package org.tec.carpooling.bl.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import org.tec.carpooling.bl.dto.UI_BL.driver.StopDTO;
import org.tec.carpooling.da.entities.*;


@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", source = "address")
    @Mapping(target = "district", source = "district")
    @Mapping(target = "startTrip", ignore = true)
    @Mapping(target = "endTrip", ignore = true)
    @Mapping(target = "auditLog", ignore = true)
    StopEntity toStopEntity(StopDTO dto);

}