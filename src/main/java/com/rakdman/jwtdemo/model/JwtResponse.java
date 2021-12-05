package com.rakdman.jwtdemo.model;

public class JwtResponse {
	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public JwtResponse() {
	}

	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	@Override
	public String toString() {
		return "JwtResponse [jwtToken=" + jwtToken + "]";
	}
	

}
