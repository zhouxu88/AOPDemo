package com.zx.aopdemo;

import android.app.Application;

/**
 * 作者： 周旭 on 2017年10月9日 0009.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public class MyApplication extends Application {
    
    public static boolean isLogin; //是否登录
    
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
