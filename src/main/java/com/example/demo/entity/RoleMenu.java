package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 这是菜单关系表
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-15
 */
@TableName("sys_role_menu")
@Data
@ApiModel(value = "RoleMenu对象", description = "这是菜单关系表")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("角色ID")
        private Integer roleId;

      @ApiModelProperty("菜单id")
        private Integer menuId;


}
