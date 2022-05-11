package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-05
 */
@TableName("sys_label")
@Data
@ApiModel(value = "Label对象", description = "")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        private Integer id;

      @ApiModelProperty("标签名")
      private String labelname;


}
