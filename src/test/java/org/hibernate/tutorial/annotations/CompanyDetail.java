package org.hibernate.tutorial.annotations;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="company_detail")
public class CompanyDetail {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@OneToOne(mappedBy="companyDetail", cascade=CascadeType.ALL)
	private Company company;
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyDetail() {
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
		return "company detail's content is address at " + this.address + ", which is occupied by company named " + this.company.getName();
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
