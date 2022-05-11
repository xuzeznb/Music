package com.example.demo.Controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.entity.Files;
import com.example.demo.mapper.FileMapper;
import com.example.demo.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 文件上传相关接口
 */
@RestController
@Controller
@RequestMapping("/file")
public class FileController {

        @Value("${file.upload.path}")
        private String fileUploadPath;

        @Value("${server.ip}")
        private String serverIp;

        @Resource
        private FileMapper fileMapper;
        @Resource
        private IFileService fileService;
        @Autowired
        private StringRedisTemplate stringRedisTemplate;

        /**
         * 图片全部查询
         * @findAll 全部
         * */
        @Autowired
        @GetMapping
//        @Cacheable(value = "files",key = "getTargetClass + methodName")
        public Result findAll(){
               return Result.success(fileMapper.selectList(null));
        }
        /**
         * 文件上传接口
         * @param file 前端传递过来的文件
         * @return
         * @throws IOException
         */
        @ResponseBody
        @PostMapping("/upload")
        public Result upload(@RequestParam MultipartFile file,@RequestParam("file") MultipartFile[] images ) throws IOException {
                String originalFilename = file.getOriginalFilename();
                String type = FileUtil.extName(originalFilename);

//                判断图片类型
                long size = file.getSize();
//                 定义一个文件唯一的标识码
                String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;

                File uploadFile = new File(fileUploadPath + fileUUID);
                // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
                File parentFile = uploadFile.getParentFile();
                if(!parentFile.exists()) {
                        parentFile.mkdirs();
                }

                String url;
                // 获取文件的md5
                String md5 = SecureUtil.md5(file.getInputStream());
                // 从数据库查询是否存在相同的记录
                Files dbFiles = getFileByMd5(md5);

                if (dbFiles != null) {
                        url = dbFiles.getUrl();
                        delete(dbFiles.getId());
                } else {
                        // 上传文件到磁盘
                        file.transferTo(uploadFile);
                        // 数据库若不存在重复文件，则不删除刚才上传的文件
                        url = "http://127.0.0.1:8010/file/"+ fileUUID;
                }
                // 存储数据库
                Files saveFile = new Files();
                saveFile.setName(originalFilename);
                saveFile.setType(type);
                saveFile.setSize(size/1024); // 单位 kb
                saveFile.setUrl(url);
                saveFile.setMd5(md5);
                fileMapper.insert(saveFile);
                // 最简单的方式：直接清空缓存
//                flushRedis(Constants.FILES_KEY);
                return Result.success(Constants.CODE_200,"上传成功",url) ;
        }

        /**
         * 文件下载接口   http://localhost:8010/file/{fileUUID}
         * @param fileUUID
         * @param response
         * @throws IOException
         */
        @GetMapping("/{fileUUID}")
        public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
                // 根据文件的唯一标识码获取文件
                File uploadFile = new File(fileUploadPath + fileUUID);
                // 设置输出流的格式
                ServletOutputStream os = response.getOutputStream();
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
                response.setContentType("application/octet-stream");
                // 读取文件的字节流
                os.write(FileUtil.readBytes(uploadFile));
                os.flush();
                os.close();
        }
        @PostMapping
        public Result save(@RequestBody Files files) {
                fileService.saveOrUpdate(files);
                return Result.success(files);
        }
        /**
         * 通过文件的md5查询文件
         * @param md5
         * @return
         */
        private Files getFileByMd5(String md5) {
                // 查询文件的md5是否存在
                QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("md5", md5);
                List<Files> filesList = fileMapper.selectList(queryWrapper);
                return filesList.size() == 0 ? null : filesList.get(0);
        }

        //    @CachePut(value = "files", key = "'frontAll'")
        @PostMapping("/update")
        public Result update(@RequestBody Files files) {
//                flushRedis(Constants.FILES_KEY);
                return Result.success(fileMapper.updateById(files));
        }

        @GetMapping("/detail/{id}")
        public Result getById(@PathVariable Integer id) {
                return Result.success(fileMapper.selectById(id));
        }

        //清除一条缓存，key为要清空的数据
//    @CacheEvict(value="files",key="'frontAll'")
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(fileMapper.deleteById(id) );
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                // select * from sys_file where id in (id,id,id...)
                QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
                queryWrapper.in("id", ids);
                List<Files> files = fileMapper.selectList(queryWrapper);
                for (Files file : files) {
                        file.setIsDelete(true);
                        fileMapper.updateById(file);
                }
                return Result.success(fileMapper.deleteBatchIds(ids));
        }

        /**
         * 分页查询接口
         * @param pageNum
         * @param pageSize
         * @param name
         * @return
         */
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String name) {

                QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
                // 查询未删除的记录
                queryWrapper.eq("is_delete", false);
                queryWrapper.orderByDesc("id");
                if (!"".equals(name)) {
                        queryWrapper.like("name", name);
                }
                return Result.success(fileMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
        }

        // 设置缓存
//        private void setCache(String key, String value) {
//                stringRedisTemplate.opsForValue().set(key, value);
//        }

        // 删除缓存
//        private void flushRedis(String key) {
//                stringRedisTemplate.delete(key);
//        }

}
