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
 * @since 2022-04-16
 */
@TableName("sys_sort")
@ApiModel(value = "Sort对象", description = "")
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String url;

    private Integer title;

    private Integer label;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getUrl() {
        return url;
    }

      public void setUrl(String url) {
          this.url = url;
      }
    
    public Integer getTitle() {
        return title;
    }

      public void setTitle(Integer title) {
          this.title = title;
      }
    
    public Integer getLabel() {
        return label;
    }

      public void setLabel(Integer label) {
          this.label = label;
      }

    @Override
    public String toString() {
        return "Sort{" +
              "id=" + id +
                  ", url=" + url +
                  ", title=" + title +
                  ", label=" + label +
              "}";
    }
}
