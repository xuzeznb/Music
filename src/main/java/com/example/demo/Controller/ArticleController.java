package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.config.AuthAccess;
import com.example.demo.config.dto.ArticleDto;
import com.example.demo.entity.Article;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/article")
        public class ArticleController {

        @Resource
        private IArticleService articleService;
        @Resource
        private ArticleMapper articleMapper;
        @Resource
        private  ArticleDto articleDto;
        @Autowired
        public ArticleController(IArticleService articleService) {
                this.articleService = articleService;
        }

        //新增文章
        @PostMapping
        public boolean save(@RequestBody Article article) {
                if (article.getTag()==""|| article.getTag()==null){
                        article.setTag("该文章未添加标签");
                }
                if (article.getLikeNum()==null || article.getLikeNum()==" "){
                        article.setLikeNum("暂无");
                }
                ArticleDto Dto01  = articleService.article(articleDto);
                String artId = Dto01.getArtId();
                article.setArtId(artId);
                return articleService.saveOrUpdate(article);

        }

        @AuthAccess
        @GetMapping("/getById/{id}")
        public Result getById(@PathVariable("id") Long id) throws Exception {
                return articleService.likeNum(id);
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(articleMapper.deleteById(id));
        }

        @GetMapping
        public List<Article> findAll() {
                return articleMapper.selectList( null);
        }

        @GetMapping("/{artId}")
        public List<Article> findOne(@PathVariable String artId) {
                return articleMapper.selectByartId(artId);
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String title) {

                QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
                queryWrapper.orderByDesc("id");
                if (!"".equals(title)) {
                        queryWrapper.like("title", title);
                }
                return Result.success(articleService.page(new Page<>(pageNum, pageSize),queryWrapper));
        }
}

