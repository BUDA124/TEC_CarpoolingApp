package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.da.repositories.GenderRepository;

import java.util.List;

@Service
public class SimpleDataRetrievalServiceImpl implements SimpleDataRetrievalService {

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public List<GenderEntity> getAllGenders() {
        return genderRepository.findAll();
    }
}
