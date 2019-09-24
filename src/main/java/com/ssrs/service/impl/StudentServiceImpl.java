package com.ssrs.service.impl;

import com.ssrs.model.Student;
import com.ssrs.mapper.StudentMapper;
import com.ssrs.service.IStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生信息表 服务实现类
 * </p>
 *
 * @author ssrs
 * @since 2019-09-23
 */
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
