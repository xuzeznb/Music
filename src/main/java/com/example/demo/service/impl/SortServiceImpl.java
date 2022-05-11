package com.example.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.demo.entity.Button;
import com.example.demo.entity.Sort;
import com.example.demo.entity.SortButton;
import com.example.demo.mapper.ButtonMapper;
import com.example.demo.mapper.SortButtonMapper;
import com.example.demo.mapper.SortMapper;
import com.example.demo.service.IButtonService;
import com.example.demo.service.ISortService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-16
 */
@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements ISortService {
//    @Resource
//    private SortButtonMapper sortButtonMapper;
//    @Resource
//    private IButtonService buttonService;

//    @Override
//    public void setSortButton(Integer buttonId, List<Integer> imgIds) {
//        //先删除当前按钮id所有绑定关系
//        sortButtonMapper.deleteBybuttonId(buttonId);
//
//        //再把前端传过来的图片ID数组绑定到当前的这个按钮id上去
//        List<Integer> imgidsCopy = CollUtil.newArrayList(imgIds);
//        for (Integer imgId: imgIds){
//               Button button = buttonService.getById(buttonId);
//            if (button.getPid()!=null && !imgidsCopy.contains(button.getPid())){
//                SortButton sortButton = new SortButton();
//                sortButton.setImgid(imgId);
//                sortButton.setButtonid(button.getPid());
//                sortButtonMapper.insert(sortButton);
//                imgidsCopy.add(button.getPid());
//            }
//
//            SortButton sortButton=new SortButton();
//            sortButton.setButtonid(buttonId);
//            sortButton.setImgid(imgId);
//            sortButtonMapper.insert(sortButton);
//        }
//    }
//
//    @Override
//    public List<Integer> getSortButton(Integer buttonId) {
//        return sortButtonMapper.selectBybuttonId(buttonId);
//    }
}
