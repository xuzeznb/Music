package com.example.demo.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.config.AuthAccess;
import com.example.demo.entity.Rotation;
import com.example.demo.service.IRotationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/rotation")
public class RotationController{

        @Resource
        private IRotationService rotationService;

        @PostMapping
        public Result save(@RequestBody Rotation rotation) {
                return Result.success(rotationService.saveOrUpdate(rotation));
        }
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(rotationService.removeById(id));
        }

        @AuthAccess
        @GetMapping
        public Result findAll() {
                return Result.success(rotationService.list());
        }

        @GetMapping("/{id}")
        public List<com.example.demo.entity.Rotation> findOne(@PathVariable Integer id) {
                return rotationService.list();
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize){
                return Result.success(rotationService.page(new Page<>(pageNum, pageSize)));
        }

}

