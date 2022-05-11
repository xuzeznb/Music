package com.example.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Button;
import com.example.demo.entity.Menu;
import com.example.demo.mapper.ButtonMapper;
import com.example.demo.service.IButtonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-16
 */
@Service
@Accessors(chain = true)
public class ButtonServiceImpl extends ServiceImpl<ButtonMapper, Button> implements IButtonService {
//        @Override
//        public List<Button> findButton(String name) {
//                QueryWrapper<Button> queryWrapper = new QueryWrapper<>();
//                if (StrUtil.isBlank(name)) {
//                    queryWrapper.like("name", name);
//                }
////            //查询所有数据
//            List<Button> list = list(queryWrapper);
//            List<Button> parentNode = list.stream().filter(button -> button.getPid() == null).collect(Collectors.toList());
//            //筛选所有数据中pid=父级id的数据就是二级菜单
//            for (Button button : parentNode) {
//                button.setChildren(list.stream().filter(m -> button.getId().equals(m.getPid())).collect(Collectors.toList()));
//            }
//                return parentNode;
//        }



}