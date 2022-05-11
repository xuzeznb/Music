package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-02
 */
public interface ArticleMapper extends BaseMapper<Article> {
 int updateNumById(Article article);



    @Select("select *  from sys_article where art_id = #{artId}")
    List<Article> selectByartId(@Param("artId")String artId);
}
