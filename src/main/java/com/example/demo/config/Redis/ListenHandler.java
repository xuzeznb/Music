//package com.example.demo.config.Redis;
//
//import com.example.demo.entity.Article;
//import com.example.demo.service.IArticleService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.redis.core.ZSetOperations;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author wl
// * @date 2022/2/22
// */
//@Slf4j
//@Component
////@EnableScheduling
////@ComponentScan(basePackages= {" com.example.demo.config.Redis"})
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class ListenHandler {
//
//    private final IArticleService articleService;
//    private final RedisUtils redisUtil;
//
//    private static final String VIEW_KEY = "viewNum";
//    private static final String COMMENT_KEY = "commentNum";
//    private static final String LIKE_KEY = "likeNum";
//
//    @Autowired
//    public ListenHandler(IArticleService articleService, RedisUtils redisUtil) {
//        this.articleService = articleService;
//        this.redisUtil = redisUtil;
//    }
//
//    @PostConstruct
//    public void init() throws Exception {
//        log.info("数据初始化开始...");
//        //将数据库中的数据写入redis
//        List<Article> articleLst = articleService.list();
//        articleLst.forEach(article -> {
//            //将浏览量、点赞数和评论数写入redis
//            redisUtil.zAdd(VIEW_KEY, article.getId().toString(), article.getViewNum());
//            redisUtil.zAdd(COMMENT_KEY, article.getId().toString(), article.getCommentNum());
//            redisUtil.zAdd(LIKE_KEY, article.getId().toString(), article.getLikeNum());
//        });
//        log.info("数据已写入redis...");
//
//    }
//
//    /**
//     * 关闭时操作
//     */
//    @PreDestroy
//    public void afterDestroy() {
//        log.info("开始关闭...");
//        //将redis中的数据写入数据库
//        Set<ZSetOperations.TypedTuple<String>> viewNum = redisUtil.zReverseRangeWithScores("viewNum", 0, 10);
//        Set<ZSetOperations.TypedTuple<String>> commentNum = redisUtil.zReverseRangeWithScores("commentNum", 0, 10);
//        Set<ZSetOperations.TypedTuple<String>> likeNum = redisUtil.zReverseRangeWithScores("likeNum", 0, 10);
//        writeNum(viewNum, VIEW_KEY);
//        writeNum(commentNum, COMMENT_KEY);
//        writeNum(likeNum, LIKE_KEY);
//        log.info("redis写入数据库完毕");
//    }
////    @Scheduled(cron="0/5 * * * * ?")
////    public void systemDateTask(){
////        System.out.println("SystemDateTask输出："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
////    }
//    @Scheduled(cron = "*/15 * * * * ?")
//    public void updateNum() {
//        log.info("周期任务开始执行...");
//        Set<ZSetOperations.TypedTuple<String>> viewNum = redisUtil.zReverseRangeWithScores("viewNum", 0, 10);
//        writeNum(viewNum, VIEW_KEY);
//        log.info("周期任务执行完毕,redis写入数据库完毕");
//    }
//
//    private void writeNum(Set<ZSetOperations.TypedTuple<String>> set, String fieldName) {
//        set.forEach(item -> {
//            Long id = Long.valueOf(item.getValue());
//            Integer num = item.getScore().intValue();
//            Article article = articleService.getById(id);
//            switch (fieldName) {
////                case VIEW_KEY:
////                    article.setViewNum(num);
////                    break;
//                case COMMENT_KEY:
//                    article.setCommentNum(num);
//                    break;
//                case LIKE_KEY:
//                    article.setLikeNum(num);
//                    break;
//                default:
//                    return;
//            }
//
//            //更新数据库
//            articleService.updateNumById(article);
//            log.info("{} 更新完毕", fieldName);
//        });
//    }
//}