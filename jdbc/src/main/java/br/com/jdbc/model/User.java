package br.com.jdbc.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private Long id;
	private String name;
	private String email;
	private List<String> phones = new ArrayList<String>();
	
	public User(String name, String email)
	{
		this.name = name;
		this.email = email;
	}
	
	public User(Long id, String name, String email)
	{
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void addPhone(String phone)
	{
		this.phones.add(phone);
	}
	
	@Override
	public String toString()
	{
		String phones = "";
		for (String phone : this.phones) {
			if (phones.isEmpty()) {
				phones = phone;
				continue;
			}
			phones = phones + "/" + phone;
		}
		
		return "[ID: " + this.id + ", NOME: " + this.name + ", EMAIL: " + this.email + ", TELEFONES: " + phones + "]";
	}
}
