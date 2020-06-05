package com.springMVCProject.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "f_name")
	@NotNull(message = "!first name is Required") 	/* for this validation add hibernate validation jar */
	@Size(min = 2,message = "!name is too small")
	@Size(max = 20,message = "!name is too Big")
	private String f_name;
	
	@Column(name = "l_name")
	@NotNull(message = "!last name is Required")
	@Size(min = 2,message = "!name is too small")
	@Size(max = 20,message = "!name is too Big")
	private String l_name;
	
	@Column(name = "email")
	@NotNull(message = "!email is Required")
	@Size(min = 8,message = "!email is too small")
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", f_name=" + f_name + ", l_name=" + l_name + ", email=" + email + "]";
	}
	
	
}
