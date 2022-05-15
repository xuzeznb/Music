package com.example.demo.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Conmment;
import com.example.demo.service.IConmmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/conmment")
public class ConmmentController {

    @Resource
    private IConmmentService conmmentService;

    @PostMapping
    public Boolean save(@RequestBody Conmment conmment) {
        return conmmentService.saveOrUpdate(conmment);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return conmmentService.removeById(id);
    }

    @GetMapping
    public List<Conmment> findAll() {
        return conmmentService.list();
    }

    @GetMapping("/tree/{articleId}")
    public List<Conmment> findTree(@PathVariable String articleId) {
        List<Conmment> articleComments = conmmentService.findCommentDetail(articleId); //查询所有的评论数据
        //查询评论数据（不包括回复）
        List<Conmment> Originlist = articleComments.stream().filter(conmment -> conmment.getOrginId() == null).collect(Collectors.toList());
//        设置评论数据的子节点也就是回复内容
        for (Conmment origin : Originlist) {
            List<Conmment> comments = articleComments.stream().filter(conmment -> origin.getId().equals(conmment.getOrginId())).collect(Collectors.toList());//表示回复对象的集合
            comments.forEach(comment -> {
                    articleComments.stream().filter(c1 -> c1.getId().equals(comment.getPid())).findFirst().ifPresent((v ->{
                        comment.setPUserId(v.getUserId());
                        comment.setPNickname(v.getNickname());
                    }));
            });
            origin.setChildren(comments);
        }
        return Originlist;
    }


    @GetMapping("/{id}")
    public List<Conmment> findOne(@PathVariable Integer id) {
        return conmmentService.list();
    }

    @GetMapping("/page")
    public Page<Conmment> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {
        return conmmentService.page(new Page<>(pageNum, pageSize));
    }

}

