package org.tec.carpooling.bl.dto.UI_BL.generalQueries;

public class TopDriverDTO {

    private final String driverName;
    private final long tripCount;

    public TopDriverDTO(String firstName, String firstSurname, long tripCount) {
        this.driverName = firstName + " " + firstSurname;
        this.tripCount = tripCount;
    }

    public String getDriverName() {
        return driverName;
    }

    public long getTripCount() {
        return tripCount;
    }

    @Override
    public String toString() {
        return driverName + " (" + tripCount + " viajes)";
    }
}