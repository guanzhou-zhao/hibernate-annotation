package org.hibernate.tutorial.annotations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company_detail")
public class CompanyDetail {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	public CompanyDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public CompanyDetail(String address) {
		this.address = address;
	}

	private String address;
	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "company detail's content is address at " + this.address;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
