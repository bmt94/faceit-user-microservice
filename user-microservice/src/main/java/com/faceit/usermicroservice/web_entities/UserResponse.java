package com.faceit.usermicroservice.web_entities;

import java.util.Objects;

public class UserResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String nickname;
	private String email;
    private String country;    
    
    public UserResponse() {
		super();
	}

	public UserResponse(Integer id, String firstName, String lastName, String nickname, String email, String country) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickname = nickname;
		this.email = email;
		this.country = country;
	}
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
    
	
	@Override
	public boolean equals(Object obj)
	{
        if (obj == this) return true; 
        if (!(obj instanceof UserResponse))return false; 
        UserResponse user = (UserResponse) obj; 

        return 
        		Integer.compare(id, user.getId()) == 0
                && firstName.equals(user.getFirstName())
				&& lastName.equals(user.getLastName())
				&& nickname.equals(user.getNickname())
				&& email.equals(user.getEmail())
				&& country.equals(user.getCountry());
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nickname, email, country);
    }
	
}
