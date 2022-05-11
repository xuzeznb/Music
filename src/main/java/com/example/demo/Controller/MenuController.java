package com.example.demo.Controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.Dict;
import com.example.demo.entity.Menu;
import com.example.demo.mapper.DictMapper;
import com.example.demo.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 青哥哥
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

        @Resource
        private IMenuService menuService;

        @Resource
        private DictMapper dictMapper;

        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody Menu menu) {
                return Result.success( menuService.saveOrUpdate(menu));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(menuService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                return Result.success(menuService.removeByIds(ids));
        }

        @GetMapping("/ids")
        public Result findAllIds() {
                return Result.success(menuService.list().stream().map(Menu::getId));
        }

        @GetMapping
        public Result findAll(@RequestParam(defaultValue = "") String name) {
                return Result.success(menuService.findMenus(name));
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(menuService.getById(id));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam String name,
                               @RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
                QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
                queryWrapper.like("name", name);
                queryWrapper.orderByDesc("id");
                return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        @GetMapping("/icons")
        public Result getIcons() {
                QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
                return Result.success(dictMapper.selectList(queryWrapper));
        }

}

