package com.jimxu.boot.service;

import com.jimxu.boot.domain.City;

/**
 * @author xuzhijing
 * 2017年11月23日 上午8:58:50
 */

public interface ICityService {
	
	City selectByCityName(String cityName);
	
	int updateByPrimaryKeySelective(City city);

}
