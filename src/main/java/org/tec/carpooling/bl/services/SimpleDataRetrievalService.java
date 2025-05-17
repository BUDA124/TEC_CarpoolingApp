package org.tec.carpooling.bl.services;

import org.tec.carpooling.da.entities.GenderEntity;

import java.util.List;

public interface SimpleDataRetrievalService {
    List<GenderEntity> getAllGenders();
}
