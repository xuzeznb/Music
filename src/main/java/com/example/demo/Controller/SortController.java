package com.example.demo.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.config.AuthAccess;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.example.demo.service.ISortService;
import com.example.demo.entity.Sort;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/sort")
        public class SortController {
        @Resource
        private ISortService sortService;

        @PostMapping
        public Result save(@RequestBody Sort sort) {
                return Result.success(sortService.saveOrUpdate(sort));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(sortService.removeById(id));
        }

        @AuthAccess
        @GetMapping("/imgs")
        public Result findAll() {
                return Result.success(sortService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(sortService.list());
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
                return Result.success(sortService.page(new Page<>(pageNum, pageSize)));
        }
//
//        @PostMapping("/sortbutton/{buttonId}")
//          public Result SortButton(@PathVariable Integer buttonId,@RequestBody List<Integer> imgids){
//                sortService.setSortButton(buttonId, imgids);
//                return Result.success();
//        }
//
//        @GetMapping("/sortbutton/{buttonId}")
//        public Result getSortButton(@PathVariable Integer buttonId) {
//                return Result.success(sortService.getSortButton(buttonId));
//        }
}