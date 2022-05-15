package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.common.Result;
import com.example.demo.config.dto.ArticleDto;
import com.example.demo.entity.Article;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-02
 */
@Transactional
public interface IArticleService extends IService<Article> {

    boolean updateNumById(Article article);


    Result likeNum(Long id);

    ArticleDto article(ArticleDto articleDto);

    Article getUserInfo(ArticleDto articleDto);

}
