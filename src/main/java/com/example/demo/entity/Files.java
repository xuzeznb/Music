package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("sys_file")
@Data
@ApiModel(value = "File对象", description = "")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("文件名字")
      private String name;

      @ApiModelProperty("文件类型")
      private String type;

      @ApiModelProperty("文件大小(kb)")
      private Long size;

      @ApiModelProperty("下载链接")
      private String url;

      @ApiModelProperty("是否删除")
      private Boolean isDelete;

      @ApiModelProperty("是否禁用链接")
      private Boolean enable;

    private String md5;


}
