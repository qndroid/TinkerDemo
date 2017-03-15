package com.youku;

import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.youku.tinker.TinkerManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadPatch(View view) {

        if (ContextCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.e("======", Environment.getExternalStorageDirectory().getAbsolutePath() + "");
            TinkerManager.loadPatch(Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed.apk");
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission.WRITE_EXTERNAL_STORAGE}, 01);
        }

    }


    public void killProcess(View view) {
        //杀掉合成patch的进程
        TinkerManager.killTinkerProcess();
        Process.killProcess(Process.myPid());
    }

    public void cleanPatch(View view) {
        TinkerManager.cleanPatch();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 01:
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed.apk");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.setBackground(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.setBackground(true);
    }
}
