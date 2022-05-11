package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-11
 */
@TableName("sys_conmment")
@ApiModel(value = "Conmment对象", description = "")
public class Conmment implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

      @ApiModelProperty("内容")
      private String content;

      @ApiModelProperty("评论人ID")
      private Integer userId;

      @ApiModelProperty("评论时间")
      private String time;

      @ApiModelProperty("父Id")
      private Integer pid;

      @ApiModelProperty("最上级评论Id")
      private Integer orginId;

      @ApiModelProperty("关联文章的Id")
      private Integer acticeId;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getContent() {
        return content;
    }

      public void setContent(String content) {
          this.content = content;
      }
    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
      }
    
    public String getTime() {
        return time;
    }

      public void setTime(String time) {
          this.time = time;
      }
    
    public Integer getPid() {
        return pid;
    }

      public void setPid(Integer pid) {
          this.pid = pid;
      }
    
    public Integer getOrginId() {
        return orginId;
    }

      public void setOrginId(Integer orginId) {
          this.orginId = orginId;
      }
    
    public Integer getActiceId() {
        return acticeId;
    }

      public void setActiceId(Integer acticeId) {
          this.acticeId = acticeId;
      }

    @Override
    public String toString() {
        return "Conmment{" +
              "id=" + id +
                  ", content=" + content +
                  ", userId=" + userId +
                  ", time=" + time +
                  ", pid=" + pid +
                  ", orginId=" + orginId +
                  ", acticeId=" + acticeId +
              "}";
    }
}
