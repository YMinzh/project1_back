package com.example.demo1.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.entity.Category;
import com.example.demo1.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    @Autowired
    CategoryMapper categoryMapper;

    @RequestMapping("/category/add")
    public JSONObject say(HttpServletRequest request , @RequestBody JSONObject res){
        JSONObject json = new JSONObject();

        System.out.println(res);

        String name = res.getString("name");
        JSONObject property;
        try {
            property = res.getJSONObject("property");
        }catch (JSONException e){
            json.put("status",200);
            json.put("data","property的类型错误");
            json.put("code",-1);
            return json;
        }

        String sort = res.getString("sort");
        String status = res.getString("status");
        //分类名字处理
        if(name == null){
            json.put("status",200);
            json.put("data","分类名字不能为空");
            json.put("code",-1);
            return json;
        }
        if(name.length()>16){
            json.put("status",200);
            json.put("data","分类名字超出字数限制");
            json.put("code",-1);
            return json;
        }

        if(categoryMapper.checkName(name).size()!=0){
            json.put("status",200);
            json.put("data","分类名字已存在");
            json.put("code",-1);
            return json;
        }

        //分类属性处理
        String newPro;
        if(property.getString("attr1").equals("")){
            newPro = "";
        }else if(property.getString("attr2").equals("")){
            property.remove("attr2");
            property.remove("attr3");
            newPro = property.toJSONString();
        }else if(property.getString("attr3").equals("")){
            property.remove("attr3");
            newPro = property.toJSONString();
        }else{
            newPro = property.toJSONString();
        }

        //排序处理
        int newSort;
        if(sort==null||sort.equals("")){
            newSort = 10;
        }else{
            try {
                newSort = Integer.parseInt(sort);
            }catch (NumberFormatException e){
                json.put("status",200);
                json.put("data","sort的类型错误");
                json.put("code",-1);
                return json;
            }
        }
        //status处理
        if (status==null){
            json.put("status",200);
            json.put("data","status不能为空");
            json.put("code",-1);
            return json;
        }
        if(!(status.equals("1") || status.equals("2"))){
            json.put("status",200);
            json.put("data","非法的status值");
            json.put("code",-1);
            return json;
        }
        char newStatus = status.charAt(0);

        Category category = new Category();
        category.setName(name);
        category.setProperty(newPro);
        category.setSort(newSort);
        category.setStatus(newStatus);

        categoryMapper.insert(category);
        json.put("status",200);
        json.put("data","success");
        json.put("code",0);
        return json;

    }






    @RequestMapping("/category/delete")
    public JSONObject say(HttpServletRequest request){
        JSONObject json = new JSONObject();

        String id = request.getParameter("id");

        try {
            Long.parseLong(id);
        }catch (NumberFormatException e){
            json.put("status",200);
            json.put("data","无效id");
            json.put("code",-1);
            return json;
        }

        Category category = categoryMapper.getOne(Long.parseLong(id));
        if (category == null){
            json.put("status",200);
            json.put("data","无效id");
            json.put("code",-1);
            return json;
        }

        categoryMapper.delete(Long.parseLong(id));
        json.put("status",200);
        json.put("data","success");
        json.put("code",0);

        return json;
    }



    @RequestMapping("/category/getall")
    public JSONObject say1(HttpServletRequest request){
        JSONObject json = new JSONObject();
        List<Category> list= categoryMapper.getAll();
//        for ( Category c:list) {
//            System.out.println(c.getCreated_at().toString());
//            System.out.println(c.getCreated_at().toLocalDateTime());
//        }
        json.put("status",200);
        json.put("data",list);
        json.put("code",0);


        return json;


    }




    @RequestMapping("/category/get")
    public JSONObject say2(HttpServletRequest request){
        long id =Long.parseLong(request.getParameter("id"));
        JSONObject json = new JSONObject();
        Category cate= categoryMapper.getOne(id);

        json.put("status",200);
        json.put("data",cate);
        json.put("code",0);


        return json;


    }




    @RequestMapping("/category/update")
    public JSONObject say3(HttpServletRequest request, @RequestBody JSONObject json1){
        System.out.println(json1);
        Category category = new Category();
        JSONObject json = new JSONObject();
        String id = json1.getString("id");
        String name = json1.getString("name");
        String property = json1.getJSONObject("property").toJSONString();
        String sort = json1.getString("sort");
        String status = json1.getString("status");
        System.out.println(property);

        if(id.equals("")){
            id = null;
        }
        if(name.equals("")){
            name = null;
        }
        if(property.equals("")){
            property = null;
        }
        if(sort.equals("")){
            sort = null;
        }
        if(status.equals("")){
            status = null;
        }


        try {
            Long.parseLong(id);
        }catch (NumberFormatException e){
            json.put("status", 200);
            json.put("data", "无效id");
            json.put("code", -1);
            return json;
        }

        Category category1 = categoryMapper.getOne(Long.parseLong(id));

        //判断id是否存在
        boolean flag = false;
        List<Category> list = categoryMapper.getAll();
        for (Category c: list) {
            if(c.getId()==Long.parseLong(id)){
                flag = true;
            }
        }
        if(!flag){
            json.put("status", 200);
            json.put("data", "无效id");
            json.put("code", -1);
            return json;
        }
        category.setId(Long.parseLong(id));
        //分类名字处理
        if(name!=null) {
            if (name.length() > 16) {
                json.put("status", 200);
                json.put("data", "分类名字超出字数限制");
                json.put("code", -1);
                return json;
            }
            category.setName(name);
        }

        category.setProperty(property);


        //排序处理
        int newSort;
        if(sort!=null){
            try {
                newSort = Integer.parseInt(sort);
            }catch (NumberFormatException e){
                json.put("status",200);
                json.put("data","sort的类型错误");
                json.put("code",-1);
                return json;
            }
            category.setSort(newSort);
        }
        //status处理
        if (status!=null) {
            if (!(status.equals("1") || status.equals("2"))) {
                json.put("status", 200);
                json.put("data", "非法的status值");
                json.put("code", -1);
                return json;
            }
            char newStatus = status.charAt(0);
            category.setStatus(newStatus);
        }

        Timestamp d = new Timestamp(System.currentTimeMillis());
        category.setUpdated_at(d);

        System.out.println(category);
        categoryMapper.update(category);

        json.put("status",200);
        json.put("data","success");
        json.put("code",0);
        return json;
    }

}
