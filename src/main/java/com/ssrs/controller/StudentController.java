package com.ssrs.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ssrs.core.manager.PageManager;
import com.ssrs.model.Student;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    /**
     * 接收 /student/index GET 请求
     * 跳转到 /webapp/WEB-INF/ftl/student/index.ftl 页面
     * @return
     *
     */
    @RequestMapping(value = "index" ,method = RequestMethod.GET)
    public String index(){
        return "student/index";   //第1个
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
    @RequestMapping(value = "add" ,method = RequestMethod.POST)
    @ResponseBody
    public Object add(Student student){
        boolean b = studentService.insert(student);
        //如果添加成功，就放回{status:200,message:'添加成功'}的JSON格式类型数据给前台 否则就返回{status:101,message:'添加失败'}
        return b? APPUtil.resultMapType(APPUtil.INSERT_SUCCESS_TYPE):APPUtil.resultMapType(APPUtil.INSERT_ERROR_TYPE);
    }

    @RequestMapping(value = "getStudentPage" ,method = RequestMethod.POST)
    @ResponseBody
    public Object getStudentPage(int page,int limit,String search){
        boolean isSearch = false;
        if (StringUtils.isNotBlank(search)){
            isSearch = true;
        }
        //简单分页查询 page当前页 limit 分页大小
        Page<Student> studentPage = studentService.selectPage(new Page<>(page, limit),new EntityWrapper<Student>().like(isSearch,"stu_name",search));
        return PageManager.buildPage(studentPage);
    }

    @RequestMapping(value = "goUpdate" ,method = RequestMethod.GET)
    public String goUpdate(long id, Model model){
        Student student = studentService.selectById(id);
        model.addAttribute("stu",student);  //底层原理：使用request.setAttribute()方法
        System.out.println("student:"+student);
        return  "student/update";  //返回视图
    }


    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    @ResponseBody
    public Object update(Student student){
        boolean b = studentService.updateById(student);
        return b?APPUtil.resultMapType(APPUtil.UPDATE_SUCCESS_TYPE):APPUtil.resultMapType(APPUtil.UPDATE_ERROR_TYPE);
    }


    @RequestMapping(value = "del" ,method = RequestMethod.POST)
    @ResponseBody
    public Object update(long id){
        boolean b = studentService.deleteById(id);
        return b?APPUtil.resultMapType(APPUtil.DELEATE_SUCCESS_TYPE):APPUtil.resultMapType(APPUtil.DELEATE_ERROR_TYPE);
    }
}

