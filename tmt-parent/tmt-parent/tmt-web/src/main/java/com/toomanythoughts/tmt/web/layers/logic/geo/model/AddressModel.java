package com.toomanythoughts.tmt.web.layers.logic.geo.model;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class AddressModel<T> extends EpicPojo {

	private Integer addressId;
	private T owner;
	private String streetAddress01;
	private String streetAddress02;
	private String city;
	private String zipCode;
	private String stateProvince;
	private String country;

	public String getStreetAddress01() {
		return this.streetAddress01;
	}

	public void setStreetAddress01(String streetAddress01) {
		this.streetAddress01 = streetAddress01;
	}

	public String getStreetAddress02() {
		return this.streetAddress02;
	}

	public void setStreetAddress02(String streetAddress02) {
		this.streetAddress02 = streetAddress02;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStateProvince() {
		return this.stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public T getOwner() {
		return this.owner;
	}

	public void setOwner(T owner) {
		this.owner = owner;
	}
}
