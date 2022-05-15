package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-11
 */
@TableName("sys_comment")
@Data
@ApiModel(value = "comment对象", description = "")
public class Conmment implements Serializable {

    private static final long serialVersionUID = 1L;
  @TableId(value = "ID", type = IdType.AUTO)

      private Integer id;

      @ApiModelProperty("内容")
      private String content;

      @ApiModelProperty("评论人ID")
      private Integer userId;

      @ApiModelProperty("评论时间")
      private String time;

      @ApiModelProperty("父Id")
      private Integer pid;
      @TableField(exist = false)
      private String pNickname;
      @TableField(exist = false)
      private Integer pUserId;

      @ApiModelProperty("最上级评论Id")
      private Integer orginId;

      @ApiModelProperty("关联文章的Id")
      private String articleId;

      @TableField(exist = false)
      private String nickname;
      @TableField(exist = false)
      private String faceUrl;
      @TableField(exist = false)
      private List<Conmment> Children;
}
