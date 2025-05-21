package org.tec.carpooling.bl.dto.UI_BL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.tec.carpooling.bl.validators.constraints.PasswordComplexity;

public class LogInDTO {
    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Username can only contain alphanumeric characters, underscores, dots, or hyphens.")
    private String username;

    @NotBlank(message = "Password is required.")
    @PasswordComplexity
    private String password;

    public LogInDTO() {}

    public LogInDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public @NotBlank(message = "Username is required.") @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.") @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Username can only contain alphanumeric characters, underscores, dots, or hyphens.") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required.") @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.") @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Username can only contain alphanumeric characters, underscores, dots, or hyphens.") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is required.") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required.") String password) {
        this.password = password;
    }
}