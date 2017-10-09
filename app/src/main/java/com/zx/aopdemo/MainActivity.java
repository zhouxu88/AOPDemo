package com.zx.aopdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zx.aopdemo.net.CheckNetAnnotation;
import com.zx.aopdemo.test.AspectJAnnotation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.check_perm_btn).setOnClickListener(this);
        findViewById(R.id.check_net_btn).setOnClickListener(this);
        findViewById(R.id.check_login_btn).setOnClickListener(this);
    }

    @AspectJAnnotation(value = Manifest.permission.CAMERA)
    public void checkPermission() {
        Log.i("tag", "检查权限");
    }

    @CheckNetAnnotation
    public void checkNet() {
        Log.i("tag", "开始检查网络状况");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_perm_btn:
                checkPermission();
                break;
            case R.id.check_net_btn:
                checkNet();
                break;
            case R.id.check_login_btn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
