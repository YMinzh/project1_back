package com.example.demo1.controller;

import com.example.demo1.entity.Nav;
import com.example.demo1.entity.Response;
import com.example.demo1.mapper.NavMapper;
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
public class NavController {

    @Autowired
    NavMapper navMapper;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/nav/add")
    public Response say(HttpServletRequest request, @RequestBody Nav nav){
        Response res = new Response();
        //类型处理
        if (nav.getType_id()!=1&&nav.getType_id()!=2&&nav.getType_id()!=3&&nav.getType_id()!=4){
            res.setCode(-1);
            res.setData("类型选择错误");
            return res;
        }

        //名字处理
        if (nav.getTitle().equals("")||nav.getTitle()==null){
            res.setData("名称不能为空");
            res.setCode(-1);
            return res;
        }
        if (nav.getTitle().length()>16){
            res.setCode(-1);
            res.setData("名称不能超过16位");
            return res;
        }

        //排序处理
        if (nav.getSort()==0){
            nav.setSort((byte) 10);
        }

        //链接类型处理
        if (nav.getLink_type()!=1&&nav.getLink_type()!=2){
            res.setData("链接类型错误");
            res.setCode(-1);
            return res;
        }

        //链接目标处理
        if (nav.getLink_target()==null||nav.getLink_target()==""){
            res.setData("链接目标不能为空");
            res.setCode(-1);
            return res;
        }

        //状态处理
        if (nav.getStatus()!=1&&nav.getStatus()!=2){
            res.setData("状态错误");
            res.setCode(-1);
            return res;
        }
        nav.setStatus((char)(nav.getStatus()+'0'));

        navMapper.insert(nav);

        res.setCode(0);
        res.setData("success");
        return res;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/nav/getall")
    public Response say(HttpServletRequest request){
        Response res = new Response();

        List<Nav> list = navMapper.getall();

        res.setCode(0);
        res.setData(list);
        return res;
    }


    @RequestMapping("/nav/update")
    public Response say1(HttpServletRequest request,@RequestBody Nav nav){
        Response res = new Response();

        //id处理
        if(navMapper.getOne(nav.getId())==null){
            res.setCode(-1);
            res.setData("id不存在");
            return res;
        }

        //类型处理
        if (nav.getType_id()!=1&&nav.getType_id()!=2&&nav.getType_id()!=3&&nav.getType_id()!=4){
            res.setCode(-1);
            res.setData("类型选择错误");
            return res;
        }

        //名字处理
        if (nav.getTitle().equals("")||nav.getTitle()==null){
            res.setData("名称不能为空");
            res.setCode(-1);
            return res;
        }
        if (nav.getTitle().length()>16){
            res.setCode(-1);
            res.setData("名称不能超过16位");
            return res;
        }

        //排序处理
        if (nav.getSort()==0){
            nav.setSort((byte) 10);
        }

        //链接类型处理
        if (nav.getLink_type()!=1&&nav.getLink_type()!=2){
            res.setData("链接类型错误");
            res.setCode(-1);
            return res;
        }

        //链接目标处理
        if (nav.getLink_target()==null||nav.getLink_target()==""){
            res.setData("链接目标不能为空");
            res.setCode(-1);
            return res;
        }

        //状态处理
        if (nav.getStatus()!='1'&&nav.getStatus()!='2'){
            res.setData("状态错误");
            res.setCode(-1);
            return res;
        }
        //更新时间处理
        Timestamp d = new Timestamp(System.currentTimeMillis());
        nav.setUpdated_at(d);

        nav.setStatus((char)(nav.getStatus()+'0'));

        navMapper.update(nav);

        res.setCode(0);
        res.setData("success");


        return res;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/nav/delete")
    public Response say1(HttpServletRequest request) {
        Response res = new Response();
        String str = request.getParameter("id");
        long id = 0;
        try {
            id = Long.parseLong(str);
        }catch (NumberFormatException e){
            res.setData("id错误");
            res.setCode(-1);
            return res;
        }
        if(navMapper.getOne(id)==null){
            res.setCode(-1);
            res.setData("id不存在");
            return res;
        }

        navMapper.delete(id);


        res.setData("success");
        res.setCode(0);
        return res;
    }
}
