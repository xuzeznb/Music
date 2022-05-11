package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SortButton对象", description = "")
@TableName("sys_sort_button")
public class SortButton {
    @ApiModelProperty("按钮ID")
    private Integer buttonid;
    @ApiModelProperty("图片ID")
    private Integer imgid;
}
