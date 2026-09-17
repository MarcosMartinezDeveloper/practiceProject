package com.marcosmartinezdeveloper.practiceProject.facade.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

//	@NotBlank(message = "Username is required")
//    @Size(min = 5, max = 20, message = "Username must have between 5 and 20 characters")
    private String username;
	
//	@NotBlank(message = "Password is required")
//	@Size(min = 8, message = "Password must have at least 8 characters")
//	@Pattern(
//	    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$",
//	    message = "Password must contain at least one uppercase letter, one lowercase letter and one special character"
//	)
	private String password;
}
