package com.example.cs_office.Model.Jwt;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;

	private final String jwttoken;
	private final String email;

	public JwtResponse(String jwttoken, String email) {
		this.jwttoken = jwttoken;
		this.email = email;
	}

}