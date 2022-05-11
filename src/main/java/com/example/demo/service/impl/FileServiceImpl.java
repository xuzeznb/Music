package com.example.demo.service.impl;

import com.example.demo.entity.Files;
import com.example.demo.mapper.FileMapper;
import com.example.demo.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小泽哥
 * @since 2022-04-15
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files> implements IFileService {

}
