/**
 * 
 */
package com.jimxu.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimxu.boot.dao.CityMapper;
import com.jimxu.boot.domain.City;

/**
 * @author xuzhijing
 *
 */

@RestController
public class CityController {
	
	
	@Autowired  
	private CityMapper cityMapper;
	
	@RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "city-name", required = true) String cityName) {
        return cityMapper.selectByCityName(cityName);
    }
}
