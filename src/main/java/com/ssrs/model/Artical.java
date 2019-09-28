package com.ssrs.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 文章信息表
 * </p>
 *
 * @author ssrs
 * @since 2019-09-28
 */
@TableName("ssrs_artical")
public class Artical implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "artical_id", type = IdType.AUTO)
    private Long articalId;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 文章标题
     */
    private String articalName;
    /**
     * 文章内容
     */
    private String articalContent;


    public Long getArticalId() {
        return articalId;
    }

    public void setArticalId(Long articalId) {
        this.articalId = articalId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getArticalName() {
        return articalName;
    }

    public void setArticalName(String articalName) {
        this.articalName = articalName;
    }

    public String getArticalContent() {
        return articalContent;
    }

    public void setArticalContent(String articalContent) {
        this.articalContent = articalContent;
    }

    @Override
    public String toString() {
        return "Artical{" +
        ", articalId=" + articalId +
        ", stuName=" + stuName +
        ", articalName=" + articalName +
        ", articalContent=" + articalContent +
        "}";
    }
}
