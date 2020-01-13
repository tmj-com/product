package com.fh.category.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.fh.category.service.CategoryService;
import com.fh.model.Category;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.util.RedisUtil;
import com.fh.util.SystemConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("tocategorylist")
    public String tolist(){
        return "/bootstrap/ztree";
    }

    @RequestMapping("tocategorylist1")
    public String tolist1(){
        return "/bootstrap/ztree";
    }

    /**
     * 根据pid查询该节点下的所有子节点
     * @param pid
     * @return
     */
    @RequestMapping(value = "queryListByPid",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse queryListByPid(Integer pid, HttpServletResponse response){
        List list = categoryService.queryListByPid( pid);
        return ServerResponse.success(list);
    }

    @RequestMapping(value = "queryList",method = RequestMethod.POST)
    @ResponseBody
    @Ignore
    public  ServerResponse  queryList(){
        /*判断是否为空*/
        List list= null;
        String s = RedisUtil.get(SystemConstant.CATEGORYLIST);
        if(StringUtils.isEmpty(s)){
          //为空查询数据
             list= categoryService.queryList();
            String toJSONString = JSON.toJSONString(list);
            //查出来的值放入redis中
         RedisUtil.set(SystemConstant.CATEGORYLIST,toJSONString);
        }
        list= JSONArray.parseArray(s);
        return ServerResponse.success(list);

    }

    @RequestMapping(value = "/addCategory",method = RequestMethod.POST)
    @ResponseBody
    public Map addCategory(Category category){
        Map map = new HashMap();
        try {
            categoryService.addCategory(category);
            map.put("status",200);
            map.put("id",category.getId());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",999);
        }
        return map;
    }

    @RequestMapping(value = "/deleteCategory",method = RequestMethod.DELETE)
    @ResponseBody
    public Map deleteCategory(@RequestParam("idList[]") List idList){
        Map map = new HashMap();
        try {
            categoryService.deleteCategory(idList);
            map.put("status",200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",999);
        }
        return map;
    }
    @RequestMapping("/getCategoryByPid")
    @ResponseBody
    public List getCategoryByPid(Integer pid){

        List list =   categoryService.queryListByPid(pid);

        return list;
    }
    @RequestMapping("/updateCategory")
    @ResponseBody
    public Map updateCategory(Category category){
        Map map = new HashMap();
        try {
            categoryService.updateCategory(category);
            map.put("status",200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",999);
        }
        return map;
    }

}
