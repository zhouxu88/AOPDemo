package com.zx.aopdemo;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zx.aopdemo.login.CheckLogin;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        radioGroup = (RadioGroup) findViewById(R.id.login_rg);
        radioGroup.setOnCheckedChangeListener(this);
        findViewById(R.id.my_attention_tv).setOnClickListener(this);
        findViewById(R.id.my_foot_print_tv).setOnClickListener(this);
        findViewById(R.id.my_shopping_cart_tv).setOnClickListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.login_rb:
                MyApplication.isLogin = true;
                break;
            case R.id.without_login_rb:
                MyApplication.isLogin = false;
                break;
        }
    }

    @CheckLogin
    @Override
    public void onClick(View v) {
        String result = "";
        switch (v.getId()) {
            case R.id.my_attention_tv:
                result = "已登录,点击了我的关注";
                break;
            case R.id.my_foot_print_tv:
                result = "已登录,点击了我的足迹";
                break;
            case R.id.my_shopping_cart_tv:
                result = "已登录,点击了我的购物车";
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}
