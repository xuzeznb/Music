package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Label;
import com.example.demo.service.ILabelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小泽哥
 * @since 2022-05-05
 */
@RestController
@RequestMapping("/label")
        public class LabelController {
    
@Resource
private ILabelService labelService;


@PostMapping
public Result save(@RequestBody Label label) {
        if (label.getLabelname()==null){
                label.setLabelname("该文章未添加标签");
        }

        return Result.success( labelService.saveOrUpdate(label));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success( labelService.removeById(id));
        }

@GetMapping
public List<Label> findAll() {
        return labelService.list();
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success( labelService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize) {
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        return Result.success(labelService.page(new Page<>(pageNum, pageSize),queryWrapper)) ;
        }

        }

