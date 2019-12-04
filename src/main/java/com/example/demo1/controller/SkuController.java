package com.example.demo1.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.entity.ForSku;
import com.example.demo1.entity.Product;
import com.example.demo1.entity.Response;
import com.example.demo1.entity.Sku;
import com.example.demo1.mapper.ProductMapper;
import com.example.demo1.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SkuController {

    @Autowired
    SkuMapper skuMapper;
    @Autowired
    com.example.demo1.mapper.ProductMapper productMapper;

    @RequestMapping("/sku/add")
    public Response say(HttpServletRequest request, @RequestBody ForSku forsku){
        Response res = new Response();
        ArrayList<Sku> arr = forsku.getData();

        //商品编号处理
        long product_id = forsku.getProduct_id();
        if (productMapper.getOne(product_id)==null){
            res.setCode(-1);
            res.setData("非法商品号");
            return res;
        }else{
            skuMapper.deleteAll(product_id);
        }

        for (Sku sku:arr) {
            sku.setProduct_id(product_id);

            //价格处理
            if (sku.getOriginal_price()==null&&sku.getPrice()!=null){
                sku.setOriginal_price(sku.getPrice());
            }else if (sku.getOriginal_price()!=null&&sku.getPrice()==null){
                sku.setPrice(sku.getOriginal_price());
            }else if (sku.getPrice()==null&&sku.getOriginal_price()==null){
                continue;
            }

            //库存处理
            if (sku.getQuantity()<=0){
                res.setData("请输入正确的库存");
                res.setCode(-1);
                return res;
            }

            //排序处理
            if (sku.getSort()==null){
                sku.setSort(10);
            }
            //状态处理
            if (sku.getStatus()==1){
                sku.setStatus((char) (1+'0'));
            }else if (sku.getStatus()==2){
                sku.setStatus((char) (2+'0'));
            }else{
                res.setCode(-1);
                res.setData("状态错误");
                return res;
            }
            System.out.println(sku.getStatus());

            skuMapper.insert(sku);
        }





        res.setCode(0);
        res.setData("success");
        return res;
    }


    @RequestMapping("/sku/getAll")
    public Response say(HttpServletRequest request){
        Response res = new Response();

        String str = request.getParameter("product_id");
        long id = 0;
        try {
            id = Long.parseLong(str);
        }catch (NumberFormatException e){
            res.setData("商品编号有误");
            res.setCode(-1);
        }


        List<Sku> list = skuMapper.getAll(id);

        res.setCode(0);
        res.setData(list);

        return res;
    }

}
