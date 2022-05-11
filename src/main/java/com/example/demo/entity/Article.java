package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-02
 */
@Data
@TableName("sys_article")
@ApiModel(value = "article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("文章id")
        @TableId(value = "ID", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("文章作者")
      private String author;

      @ApiModelProperty("创建时间")
      @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
      private Date createTime;

      @ApiModelProperty("文章内容")
      private String content;

      @ApiModelProperty("文章标签")
      private String tag;

      @ApiModelProperty("浏览量")
      private Integer viewNum;

      @ApiModelProperty("评论数")
      private Integer commentNum;

      @ApiModelProperty("点赞数")
      private String likeNum;
      @ApiModelProperty("文章ID")
      private String ArtId;
      @ApiModelProperty("")
    private String render;

      @ApiModelProperty
  private String title;


}
