package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PaymentMethodEntity;
import org.tec.carpooling.da.entities.StopEntity;
import org.tec.carpooling.da.entities.TripEntity;
import org.tec.carpooling.da.entities.TripHasStopHasPaymentMethodEntity;

import java.util.Optional;


@Repository
public interface TripHasStopHasPaymentMethodRepository extends JpaRepository<TripHasStopHasPaymentMethodEntity, Long> {
    Optional<TripHasStopHasPaymentMethodEntity> findByTripAndStopAndPaymentMethod(TripEntity tripEntity, StopEntity mallSanPedro, PaymentMethodEntity paymentMethodEntity);
}