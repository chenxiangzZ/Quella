package com.ssrs.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学生信息表
 * </p>
 *
 * @author ssrs
 * @since 2018-11-09
 */
@Data
@TableName("ssrs_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 学生年龄
     */
    private Integer stuAge;
    /**
     * 学生成绩
     */
    private Float stuGrade;
    /**
     * 学号
     */
    private String stuNumber;
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date stuCreateTime;
}
