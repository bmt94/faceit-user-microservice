package com.faceit.usermicroservice.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "User")
public class User {
	
    private Integer id;
    @NotNull
	@Size(min = 3, max = 30)
    private String firstName;
    @NotNull
	@Size(min = 3, max = 30)
    private String lastName;
	@Size(min = 3, max = 10)
    private String nickname;
    private String password;
	@Size(min = 5, max = 50)
    private String email;
	@Size(min = 3, max = 20)
    private String country;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(
    	    name = "native",
    	    strategy = "native"
    	)
    @Column(name = "ID", updatable = false, nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    
    @Column(name = "First_Name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
    @Column(name = "Last_Name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    @Column(name = "Nickname")
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
    @Column(name = "Password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    @Column(name = "Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    @Column(name = "Country")
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
        if (!(obj instanceof User))return false; 
        User user = (User) obj; 

        return 
        		Integer.compare(id, user.getId()) == 0
                && firstName.equals(user.getFirstName())
				&& lastName.equals(user.getLastName())
				&& nickname.equals(user.getNickname())
				&& password.equals(user.getPassword())
				&& email.equals(user.getEmail())
				&& country.equals(user.getCountry());
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nickname, password, email, country);
    }
	

}
