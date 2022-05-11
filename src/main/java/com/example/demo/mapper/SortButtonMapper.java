package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SortButton;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SortButtonMapper extends BaseMapper<SortButton> {
    @Delete("delete from sys_sort_button where button_id = #{buttonId}")
    int deleteBybuttonId(@Param("buttonId") Integer buttonId);

    @Select("select img_id from sys_role_menu where button_id = #{buttonId}")
    List<Integer> selectBybuttonId(@Param("buttonId")Integer buttonId);
}
