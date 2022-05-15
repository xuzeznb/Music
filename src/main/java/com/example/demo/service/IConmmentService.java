package com.example.demo.service;

import com.example.demo.entity.Conmment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-11
 */
public interface IConmmentService extends IService<Conmment> {

    List<Conmment> findCommentDetail(String articleId);
}
