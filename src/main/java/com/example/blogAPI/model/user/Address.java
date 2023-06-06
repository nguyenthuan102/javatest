package com.example.blogAPI.model.user;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.blogAPI.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor 
@Table(name = "address")
public class Address extends UserDateAudit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String street;
	
	private String suite;
	
	private String city;
	
	private String zipcode;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "geoId")
	private Geo geo;
	
	@OneToOne(mappedBy = "address")
	private User user;

	public Address(String street, String suite, String city, String zipcode, Geo geo) {
		this.street = street;
		this.suite = suite;
		this.city = city;
		this.zipcode = zipcode;
		this.geo = geo;
	}
	
	@JsonIgnore
	public Long getId() {
		return id;
	}
	
	@JsonIgnore
	@Override
	public Long getCreatedBy() {
		return super.getCreatedBy();
	}

	@JsonIgnore
	@Override
	public void setCreatedBy(Long createdBy) {
		super.setCreatedBy(createdBy);
	}

	@JsonIgnore
	@Override
	public Long getUpdatedBy() {
		return super.getUpdatedBy();
	}

	@JsonIgnore
	@Override
	public void setUpdatedBy(Long updatedBy) {
		super.setUpdatedBy(updatedBy);
	}

	@JsonIgnore
	@Override
	public Instant getCreatedAt() {
		return super.getCreatedAt();
	}

	@JsonIgnore
	@Override
	public void setCreatedAt(Instant createdAt) {
		super.setCreatedAt(createdAt);
	}

	@JsonIgnore
	@Override
	public Instant getUpdatedAt() {
		return super.getUpdatedAt();
	}

	@JsonIgnore
	@Override
	public void setUpdatedAt(Instant updatedAt) {
		super.setUpdatedAt(updatedAt);
	}
}
