package com.jimxu.boot.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.jimxu.boot.dao.CityMapper;
import com.jimxu.boot.domain.City;

/**
 * @author xuzhijing
 * 2017年11月23日 上午9:07:37
 */

@Service
public class CityServiceImpl implements ICityService {
	
	@Autowired
	CityMapper cityMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	protected RedisTemplate redisTemplate;

	@Override
	public City selectByCityName(String cityName) {
		 // 从缓存中获取OauthClient信息
        String key = "city:"+cityName;
		@SuppressWarnings("unchecked")
		ValueOperations<String, City> operations = redisTemplate.opsForValue();

        // 缓存存在
        City city=null;
        @SuppressWarnings("unchecked")
		boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	city = operations.get(key);
        	System.out.println("CityServiceImpl.selectByCityName() : 从缓存中获取了对象 >> " + city.toString());
        	return city;
        }
        
        //从DB获取
        city=cityMapper.selectByCityName(cityName);
        // 插入缓存
        if(city!=null){
           operations.set(key, city, 30, TimeUnit.DAYS);
          System.out.println("CityServiceImpl.selectByCityName() : 对象插入缓存 >> " + city.toString());
        }
		return city;
	}

	@Override
	public int updateByPrimaryKeySelective(City city) {
		int ret=cityMapper.updateByPrimaryKeySelective(city);
        // 缓存存在，删除缓存
        String key = "city:" + city.getCityName();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            System.out.println("CityServiceImpl.updateByPrimaryKeySelective() : 从缓存中删除city >> " + city.toString());
        }
		return ret;
	}
	

}
