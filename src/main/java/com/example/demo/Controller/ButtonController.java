package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Button;
import com.example.demo.service.IButtonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/button")
public class ButtonController {
@Resource
private IButtonService buttonService;

@PostMapping
public Result save(@RequestBody Button button) {
        return Result.success(buttonService.saveOrUpdate(button));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(buttonService.removeById(id));
        }
@PostMapping("/del/batch")
public Result deleteBach(@RequestBody List<Integer> ids){
        return Result.success( buttonService.removeByIds(ids));
}
@GetMapping("/ids")
public Result findAllIdS() {
        return Result.success( buttonService.list().stream().map(Button::getId));
        }
//@AuthAccess
//@GetMapping
//public Result findAll(@RequestParam(defaultValue = "") String name){
//        return Result.success(buttonService.findButton(name));
//}
@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(buttonService.getById(id));
        }
@GetMapping
public Result findAll(){
        return Result.success(buttonService.list());
}


@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
                       @RequestParam Integer name,
                       @RequestParam Integer pageSize) {
        QueryWrapper<Button>queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return Result.success(buttonService.page(new Page<>(pageNum, pageSize)));
        }
        }

