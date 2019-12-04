package com.example.demo1.mapper;

import com.example.demo1.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {


    @Select("SELECT * FROM category where status = 1 or status = 2")
    List<Category> getAll();

    @Select("SELECT * FROM category where (status = 1 or status = 2) and name = #{name}")
    List<Category> checkName(String name);

    @Select("SELECT * FROM category ORDER BY id DESC LIMIT #{offset}, #{limit}")
    List<Category> getAllByPage(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT * FROM category ORDER BY id DESC LIMIT #{page.offset}, #{page.limit}")
    List<Category> getAllByPage2(@Param("page") Map<String, Object> page);

    @Select("SELECT * FROM category WHERE id=#{id}")
    Category getOne(long id);

    @Select("SELECT * FROM category WHERE name=#{name} limit 1")
    Category findOneByUsername(String name);

    @Insert("INSERT INTO category (name, property,sort,status) VALUES (#{name}, #{property}, #{sort}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Category category);

    @Update("UPDATE category SET name =#{name}, property =#{property}, sort =#{sort}, status =#{status}, updated_at =#{updated_at} WHERE id =#{id}")
    void update(Category category);

    @Update("UPDATE category SET status =3 WHERE id =#{id}")
    void delete(long id);
}
