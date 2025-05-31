package org.tec.carpooling.bl.dto.UI_BL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DriverRegistrationDTO {

    @NotBlank(message = "Car moder cannot be null")
    @Size(max = 50, message = "Car model must not exceed 50 characters.")
    private String carModel;

}
