package com.example.demo.service;

import com.example.demo.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-15
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
