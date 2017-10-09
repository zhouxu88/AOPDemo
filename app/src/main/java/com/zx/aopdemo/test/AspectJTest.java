package com.zx.aopdemo.test;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 作者： 周旭 on 2017年10月9日 0009.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

@Aspect
public class AspectJTest {
    private static final String TAG = "tag";

    @Pointcut("execution(@com.zx.aopdemo.test.AspectJAnnotation  * *(..))")
    public void executionAspectJ() {

    }

    @Around("executionAspectJ()")
    public Object aroundAspectJ(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Log.i(TAG, "aroundAspectJ(ProceedingJoinPoint joinPoint)");
        AspectJAnnotation aspectJAnnotation = methodSignature.getMethod().getAnnotation(AspectJAnnotation.class);
        String permission = aspectJAnnotation.value();
        Context context = (Context) joinPoint.getThis();
        Object o = null;
        String result = "";
        if (PermissionManager.getInnerInstance().checkPermission(context, permission)) {
            o = joinPoint.proceed();
            Log.i(TAG, "有权限");
            result =  "有权限";
        } else {
            Log.i(TAG, "没有权限，不可以使用");
            result =  "没有权限，不可以使用";
        }
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        return o;
    }
}  
