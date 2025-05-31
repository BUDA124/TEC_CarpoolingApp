package org.tec.carpooling.bl.dto.UI_BL;

import java.time.LocalDate;

/**
 * Data Transfer Object for carrying the content and metadata of the system's
 * terms and conditions from the BL to the UI.
 */
public class TermsAndConditionsDTO {

    private String version;
    private String content;
    private LocalDate effectiveDate;
    private LocalDate lastUpdatedDate;

    public TermsAndConditionsDTO() {}

    public TermsAndConditionsDTO(String version, String content, LocalDate effectiveDate, LocalDate lastUpdatedDate) {
        this.version = version;
        this.content = content;
        this.effectiveDate = effectiveDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    // Getters and Setters
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }
    public LocalDate getLastUpdatedDate() { return lastUpdatedDate; }
    public void setLastUpdatedDate(LocalDate lastUpdatedDate) { this.lastUpdatedDate = lastUpdatedDate; }
}
