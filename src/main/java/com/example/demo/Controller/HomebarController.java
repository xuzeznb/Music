package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.config.AuthAccess;
import com.example.demo.entity.Homebar;
import com.example.demo.service.IHomebarService;
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
@RequestMapping("/homebar")
public class HomebarController {

        @Resource
        private IHomebarService homebarService;

        @PostMapping
        public Boolean save(@RequestBody Homebar homebar) {
                return homebarService.saveOrUpdate(homebar);
        }

        @DeleteMapping("/{id}")
        public Boolean delete(@PathVariable Integer id) {
                return homebarService.removeById(id);
        }
        @AuthAccess
        @GetMapping
        public List<Homebar> findAll() {
                return homebarService.list();
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(homebarService.getById(id) ) ;
        }

        @GetMapping("/page")
        public Page<Homebar> findPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String name) {
                QueryWrapper<Homebar> queryWrapper=new QueryWrapper<>();
                if (! "" .equals(name)){
                        queryWrapper.like("name",name );
                }
                return homebarService.page(new Page<>(pageNum, pageSize));
        }
}

