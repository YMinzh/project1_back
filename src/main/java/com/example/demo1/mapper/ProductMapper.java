package com.example.demo1.mapper;

import com.example.demo1.entity.Details;
import com.example.demo1.entity.Attrs;
import com.example.demo1.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ProductMapper {


    @Select("SELECT * FROM product ORDER BY sort DESC")
    List<Product> getAll();

    @Select("select a.type,a.attr,e.property,e.id,e.category_id\n" +
            "from .pre_attrs a right join (\n" +
            "select c.property property,p.id id,p.category_id category_id\n" +
            "from category c right join product p\n" +
            "on c.id=p.category_id\n" +
            ") e\n" +
            "on a.product_id = e.id\n" +
            "where e.id = #{id}")
    List<Details> getDetails(long id);

    @Select("SELECT * FROM product ORDER BY id DESC LIMIT #{offset}, #{limit}")
    List<Product> getAllByPage(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT * FROM product ORDER BY id DESC LIMIT #{page.offset}, #{page.limit}")
    List<Product> getAllByPage2(@Param("page") Map<String, Object> page);

    @Select("SELECT * FROM product WHERE id=#{id}")
    Product getOne(long id);

    @Select("SELECT attr FROM pre_attrs WHERE product_id=#{product_id} and type=#{type}")
    List<String> getAttrs(Attrs attrs);

    @Select("SELECT * FROM product WHERE name=#{name} limit 1")
    Product findOneByUsername(String name);

    @Insert("INSERT INTO product (name, category_id, sale_num, content, sort ,status) VALUES (#{name},#{category_id},#{sale_num},#{content},#{sort},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insert(Product product);

    @Insert("INSERT INTO pre_attrs (product_id, type, attr) VALUES (#{product_id},#{type},#{attr})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long insertPro(Attrs attrs);

    @Update("UPDATE product SET sort=#{sort}, status=#{status}, updated_at=#{updated_at}, content=#{content}, name=#{name,jdbcType=VARCHAR}  WHERE id =#{id}" )
    void update(Product product);

    @Update("UPDATE FROM product SET status=3 WHERE id =#{id}")
    void delete(long id);

    @Delete("delete from pre_sku where id = #{id}")
    void deleteAttrs(long id);
}
