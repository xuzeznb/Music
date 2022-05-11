package com.example.demo.config.Redis;

import com.example.demo.service.IArticleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wl
 * @date 2022/2/22
 */
//指定为切面类
@Aspect
@Component
public class MyAspect {
    private final IArticleService articleService;
    private final RedisUtils redisUtil;

    public MyAspect(IArticleService articleService, RedisUtils redisUtil) {
        this.articleService = articleService;
        this.redisUtil = redisUtil;
    }

    //定义一个名为"myPointCut()"的切面，getById()这个方法中
    @Pointcut("execution(public * com.example.demo.Controller.ArticleController.getById(..))")
    public void myPointCut(){}

    //在这个方法执行后
    @After("myPointCut()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        Object[] objs=joinPoint.getArgs();
        Long id=(Long) objs[0];
        //根据id更新浏览量
        redisUtil.zIncrementScore("viewNum",id.toString(),1);
    }
}