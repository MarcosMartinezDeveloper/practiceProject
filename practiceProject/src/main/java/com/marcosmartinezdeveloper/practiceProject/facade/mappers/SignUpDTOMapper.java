package com.marcosmartinezdeveloper.practiceProject.facade.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marcosmartinezdeveloper.practiceProject.facade.dtos.SignUpDTO;
import com.marcosmartinezdeveloper.practiceProject.persistence.entities.User;

@Mapper(componentModel = "spring")
public interface SignUpDTOMapper {
	
	//This is an unnecesary method but it is useful for practicing with MapStruct
	@Mapping(source = "password", target = "confirmationPassword")
	SignUpDTO toDTO(User user);
	
	@Mapping(target = "role", constant = "CUSTOMER")
	User toCustomerEntity(SignUpDTO signUpDTO);
}
