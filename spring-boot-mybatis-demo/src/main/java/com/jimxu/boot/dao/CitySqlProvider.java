package com.jimxu.boot.dao;

import com.jimxu.boot.domain.City;
import org.apache.ibatis.jdbc.SQL;

public class CitySqlProvider {

    public String insertSelective(City record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("city");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getProvinceId() != null) {
            sql.VALUES("province_id", "#{provinceId,jdbcType=TINYINT}");
        }
        
        if (record.getCityName() != null) {
            sql.VALUES("city_name", "#{cityName,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(City record) {
        SQL sql = new SQL();
        sql.UPDATE("city");
        
        if (record.getProvinceId() != null) {
            sql.SET("province_id = #{provinceId,jdbcType=TINYINT}");
        }
        
        if (record.getCityName() != null) {
            sql.SET("city_name = #{cityName,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}