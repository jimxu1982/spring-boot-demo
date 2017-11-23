package com.jimxu.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimxu.boot.domain.City;
import com.jimxu.boot.service.CityServiceImpl;

/**
 * @author xuzhijing
 * 2017年11月23日 上午9:28:50
 */

@RestController
public class CityController {
	
	
	@Autowired  
	private CityServiceImpl cityServiceImpl;
	
	@RequestMapping(value = "/api/get-city", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "city-name", required = true) String cityName) {
        return cityServiceImpl.selectByCityName(cityName);
    }
	
	@RequestMapping(value = "/api/update-city", method = RequestMethod.GET)
    public City updateCity(@RequestParam(value = "city-name", required = true) String cityName,@RequestParam(value = "city-desc", required = true) String description) {
        City city = cityServiceImpl.selectByCityName(cityName);
        city.setDescription(description);
        this.cityServiceImpl.updateByPrimaryKeySelective(city);
        return city;
    }
}
