package com.ssrs.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ssrs.core.manager.PageManager;
import com.ssrs.model.Artical;
import com.ssrs.model.Student;
import com.ssrs.service.IArticalService;
import com.ssrs.service.IStudentService;
import com.ssrs.util.commom.APPUtil;
import com.ssrs.util.commom.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 学生信息表 前端控制器
 * </p>
 *
 * @author ssrs
 * @since 2019-09-20
 */
@Controller
@RequestMapping("/artical")
public class ArticalController {


    @Autowired
    private IStudentService studentService;

    /**
     * 接收 /student/index GET 请求
     * 跳转到 /webapp/WEB-INF/ftl/artical/index.ftl 页面
     * @return
     *
     */
    @RequestMapping(value = "index" ,method = RequestMethod.GET)
    public String index(){
        return "artical/index";   //第1个
    }

    /**
     * 接收 /student/goAdd GET 请求
     * 跳转到 /webapp/WEB-INF/ftl/student/add.ftl 页面
     * @return
     */
    @RequestMapping(value = "goAdd" ,method = RequestMethod.GET)
    public String goAdd(){
        return "student/add";  // add 第一个
    }

    /**
     * 接收 /student/add POST 请求
     * 加上 @ResponseBody 就代表返回JSON数据
     * 不加 就是返回视图页面
     * @return
     */

    //index时，表格渲染加载此处
    @RequestMapping(value = "getArticalPage" ,method = RequestMethod.POST)
    @ResponseBody
    public Object getArticalPage(int page,int limit,String search){
        boolean isSearch = false;
        if (StringUtils.isNotBlank(search)){
            isSearch = true;
        }
        //简单分页查询 page当前页 limit 分页大小
        Page<Student> studentPage = studentService.selectPage(new Page<>(page, limit),new EntityWrapper<Student>().like(isSearch,"stu_name",search));
        return PageManager.buildPage(studentPage);  //返回的是一个Map,(Code、MSG、Count、Data)
    }

}

