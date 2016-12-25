package org.hibernate.tutorial.annotations;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="COMPANY2")
public class Company {
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@OneToOne
	@JoinColumn(name="detail_id")
	private CompanyDetail companyDetail;
	public CompanyDetail getCompanyDetail() {
		return companyDetail;
	}

	public void setCompanyDetail(CompanyDetail companyDetail) {
		this.companyDetail = companyDetail;
	}

	public Company(String name) {
		this.name = name;
	}

	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Version
	private Date version;
	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + " company's address is at " + this.companyDetail.getAddress();
	}

	public Company() {
		
	}

}
