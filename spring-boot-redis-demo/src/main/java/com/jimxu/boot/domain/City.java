package com.jimxu.boot.domain;

import java.io.Serializable;

public class City implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
    private Long id;

    private Byte provinceId;

    private String cityName;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Byte provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName=" + cityName +
                ", provinceId='" + provinceId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}