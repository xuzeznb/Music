package com.example.demo.mapper;

import com.example.demo.config.dto.UserPasswordDTO;
import com.example.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-15
 */
public interface UserMapper extends BaseMapper<User> {
    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

    Page<User> findPage(Page<User> page, @Param("username") String username,@Param("userid") String userid,
                        @Param("email") String email, @Param("address") String address);
}