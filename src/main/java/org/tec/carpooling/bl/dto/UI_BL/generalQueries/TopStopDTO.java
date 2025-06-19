package org.tec.carpooling.bl.dto.UI_BL.generalQueries;

public class TopStopDTO {

    private final String stopAddress;
    private final long usageCount;

    public TopStopDTO(String stopAddress, long usageCount) {
        this.stopAddress = stopAddress;
        this.usageCount = usageCount;
    }

    public String getStopAddress() {
        return stopAddress;
    }

    public long getUsageCount() {
        return usageCount;
    }

    @Override
    public String toString() {
        return stopAddress + " (" + usageCount + " veces)";
    }
}