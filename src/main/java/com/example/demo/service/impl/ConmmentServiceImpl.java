package com.example.demo.service.impl;

import com.example.demo.entity.Conmment;
import com.example.demo.mapper.ConmmentMapper;
import com.example.demo.service.IConmmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-11
 */
@Service
public class ConmmentServiceImpl extends ServiceImpl<ConmmentMapper, Conmment> implements IConmmentService {

        @Resource
        private ConmmentMapper conmmentMapper;
    @Override
    public List<Conmment> findCommentDetail(String articleId) {
            return conmmentMapper.findCommentDetail(articleId);
    }
}
