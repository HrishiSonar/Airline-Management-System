package com.app.dtos;


import com.app.entities.RoleEnum;
import com.app.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    
    @JsonProperty
	private String name;
    @JsonProperty
	private String email;
    @JsonProperty
	private String cpass;
    @JsonProperty
	private UserRole role;
}

