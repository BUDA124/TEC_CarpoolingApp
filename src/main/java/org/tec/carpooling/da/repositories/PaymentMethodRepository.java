package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.PaymentMethodEntity;

public class PaymentMethodRepository extends BaseRepository<PaymentMethodEntity, Long> {

    public PaymentMethodRepository() {
        super(PaymentMethodEntity.class);
    }
}