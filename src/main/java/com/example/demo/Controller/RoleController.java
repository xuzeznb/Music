package com.example.demo.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Role;
import com.example.demo.service.IRoleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 青哥哥
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/role")
public class RoleController {

        @Resource
        private IRoleService roleService;

        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody Role role) {
                return Result.success(roleService.saveOrUpdate(role));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(roleService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {

                return Result.success(roleService.removeByIds(ids));
        }

        @GetMapping
        public Result findAll() {
                return Result.success(roleService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(roleService.getById(id));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam String name,
                               @RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
                QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
                queryWrapper.like("name", name);
                queryWrapper.orderByDesc("id");
                return Result.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        /**
         * 绑定角色和菜单的关系
         * @param roleId 角色id
         * @param menuIds 菜单id数组
         * @return
         */
        @PostMapping("/roleMenu/{roleId}")
        public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
                return Result.success(roleService.setRoleMenu(roleId, menuIds));
        }

        @GetMapping("/roleMenu/{roleId}")
        public Result getRoleMenu(@PathVariable Integer roleId) {
                return Result.success( roleService.getRoleMenu(roleId));
        }

}

