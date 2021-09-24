package shopApp.entity;

import java.sql.Timestamp;

public class Products {

	private int id;

	private String name;

	private int price;

	private Products_status status;

	private Timestamp created_at;

	public Products(int id, String name, int price, Products_status status, Timestamp created_at) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.status = status;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Products_status getStatus() {
		return status;
	}

	public void setStatus(Products_status status) {
		this.status = status;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
}
