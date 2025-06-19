package org.tec.carpooling.bl.dto.UI_BL.stats;

public class ChartDataDTO {

    private String label;
    private Number value;

    public ChartDataDTO(String label, Number value) {
        this.label = label;
        this.value = value;
    }

    // Getters y Setters
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}