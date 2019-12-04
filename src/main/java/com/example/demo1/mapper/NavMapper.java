package com.example.demo1.mapper;

import com.example.demo1.entity.Category;
import com.example.demo1.entity.Nav;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NavMapper {

    @Insert("INSERT INTO pre_nav (title, type_id,picture,link_type,link_target,sort,status) VALUES (#{title}, #{type_id}, #{picture}, #{link_type}, #{link_target}, #{sort}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Nav nav);

    @Select("select *from pre_nav where status = 1 or status =2")
    List<Nav> getall();

    @Select("select *from pre_nav where id = #{id}")
    Nav getOne(long id);

    @Update("update pre_nav set title=#{title}, type_id=#{type_id}, picture=#{picture}, link_type=#{link_type}, link_target=#{link_target}, sort=#{sort}, status=#{status},updated_at=#{updated_at} where id=#{id}")
    void  update(Nav nav);

    @Update("update pre_nav set status = 3 where id = #{id}")
    void delete(long id);
}
