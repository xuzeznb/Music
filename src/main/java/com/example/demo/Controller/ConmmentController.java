package com.example.demo.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Conmment;
import com.example.demo.service.IConmmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


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

