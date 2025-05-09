package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.DailyReportEntity;

public class DailyReportRepository extends BaseRepository<DailyReportEntity, Long> {

    public DailyReportRepository() {
        super(DailyReportEntity.class);
    }
}