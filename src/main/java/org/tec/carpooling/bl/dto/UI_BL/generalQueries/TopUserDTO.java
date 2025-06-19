package org.tec.carpooling.bl.dto.UI_BL.generalQueries;

public class TopUserDTO {

    private final String userName;
    private final long tripCount;

    public TopUserDTO(String firstName, String firstSurname, long tripCount) {
        this.userName = firstName + " " + firstSurname;
        this.tripCount = tripCount;
    }

    public String getUserName() {
        return userName;
    }

    public long getTripCount() {
        return tripCount;
    }

    @Override
    public String toString() {
        return userName + " (" + tripCount + " viajes)";
    }
}