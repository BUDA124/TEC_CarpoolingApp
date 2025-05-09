package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.AdminReceiveDailyReportEntity;
import org.tec.carpooling.da.entities.embeddable.AdminReceiveDailyReportId; // Assuming this PK class exists

public class AdminReceiveDailyReportRepository extends BaseRepository<AdminReceiveDailyReportEntity, AdminReceiveDailyReportId> {

    public AdminReceiveDailyReportRepository() {
        super(AdminReceiveDailyReportEntity.class);
    }
}