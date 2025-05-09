package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.TripReportDailyReportEntity;
import org.tec.carpooling.da.entities.embeddable.TripReportDailyReportId;
import org.tec.carpooling.da.entities.embeddable.TripReportDailyReportId;

public class TripReportDailyReportRepository extends BaseRepository<TripReportDailyReportEntity, TripReportDailyReportId> {

    public TripReportDailyReportRepository() {
        super(TripReportDailyReportEntity.class);
    }
}