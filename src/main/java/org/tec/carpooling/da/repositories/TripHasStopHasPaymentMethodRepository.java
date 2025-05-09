package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.TripHasStopHasPaymentMethodEntity;
import org.tec.carpooling.da.entities.embeddable.TripHasStopHasPaymentMethodId; // Assuming this PK class exists

public class TripHasStopHasPaymentMethodRepository extends BaseRepository<TripHasStopHasPaymentMethodEntity, TripHasStopHasPaymentMethodId> {

    public TripHasStopHasPaymentMethodRepository() {
        super(TripHasStopHasPaymentMethodEntity.class);
    }
}