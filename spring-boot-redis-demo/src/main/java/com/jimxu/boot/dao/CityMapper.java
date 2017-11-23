package com.jimxu.boot.dao;

import com.jimxu.boot.domain.City;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface CityMapper {
    @Delete({
        "delete from city",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into city (id, province_id, ",
        "city_name, description)",
        "values (#{id,jdbcType=BIGINT}, #{provinceId,jdbcType=TINYINT}, ",
        "#{cityName,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR})"
    })
    int insert(City record);

    @InsertProvider(type=CitySqlProvider.class, method="insertSelective")
    int insertSelective(City record);

    @Select({
        "select",
        "id, province_id, city_name, description",
        "from city",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="province_id", property="provinceId", jdbcType=JdbcType.TINYINT),
        @Result(column="city_name", property="cityName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    City selectByPrimaryKey(Long id);
    
    @Select({
        "select",
        "id, province_id, city_name, description",
        "from city",
        "where city_name = #{city_name,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="province_id", property="provinceId", jdbcType=JdbcType.TINYINT),
        @Result(column="city_name", property="cityName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    City selectByCityName(String cityName);

    @UpdateProvider(type=CitySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(City record);

    @Update({
        "update city",
        "set province_id = #{provinceId,jdbcType=TINYINT},",
          "city_name = #{cityName,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(City record);
}