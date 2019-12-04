package com.example.demo1.mapper;

import com.example.demo1.entity.Sku;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SkuMapper {

    @Insert("INSERT INTO pre_sku (product_id, original_price, price, attr1, attr2 , attr3, quantity, sort, status) VALUES (#{product_id},#{original_price},#{price},#{attr1},#{attr2},#{attr3},#{quantity},#{sort},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insert(Sku sku);

    @Delete("delete from pre_sku where product_id=#{id}")
    void deleteAll(long id);

    @Select("select *from pre_sku where product_id=#{id}")
    List<Sku> getAll(long id);

}
