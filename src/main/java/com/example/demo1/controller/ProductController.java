package com.example.demo1.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.entity.*;
import com.example.demo1.mapper.CategoryMapper;
import com.example.demo1.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {


    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Transactional(rollbackFor=Exception.class)
    @RequestMapping("/product/add")
    public Response say(HttpServletRequest request , @RequestBody JSONObject pro){
        Response res = new Response();
        Product product = new Product();
        //名字处理
        if (pro.getString("name")==null||pro.getString("name").equals("")){
            res.setCode(-1);
            res.setData("商品名字不能为空!");
            return res;
        }
        if (pro.getString("name").length()>16){
            res.setCode(-1);
            res.setData("商品名字不能大于16!");
            return res;
        }
        product.setName(pro.getString("name"));
        //分类id处理
        if (pro.getLong("category_id")==null||pro.getLong("category_id")<=0L){
            res.setCode(-1);
            res.setData("分类不能为空!");
            return res;
        }
//        if(categoryMapper.getOne(pro.getCategory_id())==null){
//            res.setCode(-1);
//            res.setData("分类不存在!");
//            return res;
//        }
        categoryMapper.getOne(pro.getLong("category_id"));
        if (categoryMapper.getOne(pro.getLong("category_id"))==null){
            res.setCode(-1);
            res.setData("分类不存在");
            return res;
        }
        product.setCategory_id(pro.getLong("category_id"));
        //销量处理
        product.setSale_num(0);

        //排序处理
        if(pro.getIntValue("sort")==0){
            product.setSort(10);
        }else{
            product.setSort(pro.getIntValue("sort"));
        }
        //状态处理
        System.out.println(pro.getByte("status"));
        if (!(pro.getIntValue("status")==1||pro.getIntValue("status")==2)){
            res.setCode(-1);
            res.setData("状态值有误");
            return res;
        }
        product.setStatus((char)(pro.getIntValue("status")+'0'));
        System.out.println(product.getStatus());


        //sql语句执行
        productMapper.insert(product);
        Long id = product.getId();

        //分类属性的处理
        Attrs attrs = new Attrs();

        JSONArray attr1 = pro.getJSONArray("attrList1");
        if(attr1.size()>0){
            for (int i = 0; i < attr1.size(); i++){
                attrs.setAttr(attr1.getString(i));
                attrs.setType(1);
                attrs.setProduct_id(id);
                productMapper.insertPro(attrs);
            }
        }
        JSONArray attr2 = pro.getJSONArray("attrList2");
        if(attr2.size()>0){
            for (int i = 0; i < attr2.size(); i++){
                attrs.setAttr(attr2.getString(i));
                attrs.setType(2);
                attrs.setProduct_id(id);
                productMapper.insertPro(attrs);
            }
        }
        JSONArray attr3 = pro.getJSONArray("attrList3");
        if(attr3.size()>0){
            for (int i = 0; i < attr3.size(); i++){
                attrs.setAttr(attr3.getString(i));
                attrs.setType(3);
                attrs.setProduct_id(id);
                productMapper.insertPro(attrs);
            }
        }



        res.setData("success");
        res.setCode(0);
        return res;
    }


    @RequestMapping("/product/delete")
    public Response delete(HttpServletRequest request, @RequestBody JSONObject req){
        Response res = new Response();
        long id = req.getLong("id");


        Product product = productMapper.getOne(id);
        if (product == null){
            res.setCode(-1);
            res.setData("无效的ID");
        }

        productMapper.delete(id);
        res.setCode(0);
        res.setData("success");

        return res;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/product/getall")
    public Response say(HttpServletRequest request){
        Response res = new Response();
        List<Product> list= productMapper.getAll();
//        for ( Category c:list) {
//            System.out.println(c.getCreated_at().toString());
//            System.out.println(c.getCreated_at().toLocalDateTime());
//        }
        res.setData(list);


        return res;


    }

    @RequestMapping("/category/getone")
    public Response getOne(HttpServletRequest request){
        Response res = new Response();

        String str = request.getParameter("id");
        long id = 0;
        try {
            id = Long.parseLong(str);
        }catch(NumberFormatException e){
            res.setCode(-1);
            res.setData("无效id");
            return res;
        }

        Category category = categoryMapper.getOne(id);
        if (category == null){
            res.setCode(-1);
            res.setData("无效id");
            return res;
        }

        res.setData(category);
        res.setCode(0);
        return res;
    }



    @RequestMapping("/product/details")
    public Response getDetails(HttpServletRequest request){

        String str = request.getParameter("id");

        Response res = new Response();
        List<Details> list = productMapper.getDetails(Long.parseLong(str));

        Details details = new Details();
        int i = 0;

        details.setId(Long.parseLong(str));
        ArrayList a = new ArrayList();
        ArrayList b = new ArrayList();
        ArrayList c = new ArrayList();

        details.setAttrList1(a);
        details.setAttrList2(b);
        details.setAttrList3(c);

        for (Details d : list){
            if(i==0){
                details.setProperty(d.getProperty());
                details.setCategory_id(d.getCategory_id());
                i++;
            }
            if (d.getType()==1){
                ArrayList arr = details.getAttrList1();
                arr.add(d.getAttr());
                details.setAttrList1(arr);
            }else if (d.getType()==2){
                ArrayList arr = details.getAttrList2();
                arr.add(d.getAttr());
                details.setAttrList2(arr);
            }else if (d.getType()==3){
                ArrayList arr = details.getAttrList3();
                arr.add(d.getAttr());
                details.setAttrList3(arr);
            }
        }




        res.setData(details);
        res.setCode(0);

        return res;


    }

    @RequestMapping("/product/update")
    public Response update(HttpServletRequest request , @RequestBody JSONObject pro){
        Response res = new Response();
        Product product = new Product();
        //id处理
        long id = pro.getLongValue("id");

        product.setId(id);
        System.out.println(id+"-------------------------------");
        System.out.println(pro);

        //名字处理
        if (pro.getString("name")==null||pro.getString("name").equals("")){
            res.setCode(-1);
            res.setData("商品名字不能为空!");
            return res;
        }
        if (pro.getString("name").length()>16){
            res.setCode(-1);
            res.setData("商品名字不能大于16!");
            return res;
        }
        product.setName(pro.getString("name"));

        //销量处理
        product.setSale_num(0);

        //排序处理
        if(pro.getIntValue("sort")==0||pro.getString("sort").equals("")){
            product.setSort(10);
        }else{
            product.setSort(pro.getIntValue("sort"));
        }
        //状态处理
        if (!(pro.getIntValue("status")==1||pro.getIntValue("status")==2)){
            res.setCode(-1);
            res.setData("状态值有误");
            return res;
        }
        product.setStatus((char)(pro.getIntValue("status")+'0'));
        //更新时间处理
        Timestamp d = new Timestamp(System.currentTimeMillis());
        product.setUpdated_at(d);

        System.out.println(product);
        //sql语句执行
        productMapper.update(product);


//        分类属性的处理
        Attrs attrs = new Attrs();

        productMapper.deleteAttrs(id);

        JSONArray attr1 = pro.getJSONArray("attrList1");
        if(attr1.size()>0){
            for (int i = 0; i < attr1.size(); i++){
                attrs.setAttr(attr1.getString(i));
                attrs.setType(1);
                attrs.setProduct_id(id);
                productMapper.insertPro(attrs);
            }
        }
        JSONArray attr2 = pro.getJSONArray("attrList2");
        if(attr2.size()>0){
            for (int i = 0; i < attr2.size(); i++){
                attrs.setAttr(attr2.getString(i));
                attrs.setType(2);
                attrs.setProduct_id(id);
                productMapper.insertPro(attrs);
            }
        }
        JSONArray attr3 = pro.getJSONArray("attrList3");
        if(attr3.size()>0){
            for (int i = 0; i < attr3.size(); i++){
                attrs.setAttr(attr3.getString(i));
                attrs.setType(3);
                attrs.setProduct_id(id);
                productMapper.insertPro(attrs);
            }
        }




        res.setData("success");
        res.setCode(0);
        return res;
    }


}
