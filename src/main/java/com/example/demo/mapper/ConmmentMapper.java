package com.example.demo.mapper;

import com.example.demo.entity.Conmment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-11
 */
public interface ConmmentMapper extends BaseMapper<Conmment> {
        @Select("SELECT   c.* , u.nickname,u.face_url  FROM sys_comment c LEFT JOIN sys_user u ON c.user_id=u.user_id" +
                " WHERE c.article_id=#{articleId} order by id desc")
    List<Conmment> findCommentDetail(@Param("articleId") String articleId);
}
