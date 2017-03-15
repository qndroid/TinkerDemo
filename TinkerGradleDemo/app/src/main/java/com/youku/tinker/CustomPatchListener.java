package com.youku.tinker;

import android.app.ActivityManager;
import android.content.Context;

import com.tencent.tinker.lib.listener.DefaultPatchListener;

/**
 * @author: vision
 * @function:
 * @date: 17/3/15
 */
public class CustomPatchListener extends DefaultPatchListener {

    private static final String TAG = "Tinker.CustomPatchListener";

    protected static final long NEW_PATCH_RESTRICTION_SPACE_SIZE_MIN = 60 * 1024 * 1024;

    private final int maxMemory;

    public CustomPatchListener(Context context) {
        super(context);
        maxMemory = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
    }

    //检查是否是合法的patch
    @Override
    protected int patchCheck(String path) {
        return super.patchCheck(path);
    }


    //获取到合法的patch后，真正patch前的监听
    @Override
    public int onPatchReceived(String path) {

        return super.onPatchReceived(path);
    }


}
