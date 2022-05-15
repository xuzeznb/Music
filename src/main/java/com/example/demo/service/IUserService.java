package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.config.dto.UserDto;
import com.example.demo.config.dto.UserPasswordDTO;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-15
 */
public interface IUserService extends IService<User> {

    UserDto login(UserDto userDto);

    User register(UserDto userDto);

    Object updatePassword(UserPasswordDTO userPasswordDTO);
    Page<User> findPage(Page<User> page, String username, String userid, String email, String address);

}
