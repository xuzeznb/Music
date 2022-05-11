package com.example.demo.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.config.dto.ArticleDto;
import com.example.demo.config.tool.IDUtil;
import com.example.demo.entity.Article;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-02
 */

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Resource
    private IArticleService articleService;
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public boolean updateNumById(Article article) {
        return baseMapper.updateNumById(article) > 0;
    }

    @Override
    public Result likeNum(Long id) {
        return null;
    }

    @Override
    public ArticleDto article(ArticleDto articleDto) {
        String articleId = IDUtil.uuid();
        articleDto.setArtId(articleId);
        return articleDto;
    }


    @Override
    public Article getUserInfo(ArticleDto articleDto) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("artId", articleDto.getArtId());
        Article one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            Log.get().error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }


}
