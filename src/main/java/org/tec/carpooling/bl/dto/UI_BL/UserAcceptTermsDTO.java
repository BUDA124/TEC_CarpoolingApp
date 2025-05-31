package org.tec.carpooling.bl.dto.UI_BL;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for conveying a user's acceptance of the terms and conditions
 * from the UI to the BL.
 */
public class UserAcceptTermsDTO {

    @AssertTrue(message = "You must accept the terms and conditions.")
    private boolean accepted;

    public UserAcceptTermsDTO() {}

    public UserAcceptTermsDTO(boolean accepted) {
        this.accepted = accepted;
    }

    // Getter and Setter
    public boolean isAccepted() { return accepted; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }
}