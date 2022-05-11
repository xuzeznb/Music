package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-16
 */
@TableName("sys_button")
@Data
@ApiModel(value = "Button对象", description = "")
public class Button implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

        private String name;

        private Integer Pid;

        private String url;
    @TableField(exist = false)
    private List<Button> children;

}
