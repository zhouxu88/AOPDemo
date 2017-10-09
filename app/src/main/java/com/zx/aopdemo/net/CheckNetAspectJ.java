package com.zx.aopdemo.net;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
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
public class CheckNetAspectJ {
    private static final String TAG = "CheckNet";

    /**
     * 找到处理的切点
     * * *(..)  可以处理所有的方法
     */
    @Pointcut("execution(@com.zx.aopdemo.net.CheckNetAnnotation  * *(..))")
    public void executionCheckNet() {
    }

    /**
     * 处理切面
     *
     * @param joinPoint
     * @return
     */
    @Around("executionCheckNet()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("TAG", "checkNet");
        // 做埋点  日志上传  权限检测（我写的，RxPermission , easyPermission） 网络检测
        // 网络检测
        // 1.获取 CheckNet 注解  NDK  图片压缩  C++ 调用Java 方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNetAnnotation checkNet = signature.getMethod().getAnnotation(CheckNetAnnotation.class);
        if (checkNet != null) {
            // 2.判断有没有网络  怎么样获取 context?
            Object object = joinPoint.getThis();// View Activity Fragment ； getThis() 当前切点方法所在的类
            Context context = getContext(object);
            if (context != null) {
                if (!NetworkManager.isNetworkConnected(context)) {
                    // 3.没有网络不要往下执行
                    Log.i(TAG, "checkNet: 网络异常");
                    Toast.makeText(context, "请检查您的网络", Toast.LENGTH_LONG).show();
                } else {
                    Log.i(TAG, "checkNet: 网络状况良好");
                    Toast.makeText(context, "网络状况良好", Toast.LENGTH_LONG).show();
                }
                return null;
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }
}
