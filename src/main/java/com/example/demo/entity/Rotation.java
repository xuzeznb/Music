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
 * @since 2022-04-15
 */
@TableName("sys_Rotation")
@Data
@ApiModel(value = "Rotation对象", description = "")
public class Rotation implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String imgurl;

    private String name;


}
