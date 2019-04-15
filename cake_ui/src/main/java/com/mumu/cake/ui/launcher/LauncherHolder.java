package com.mumu.cake.ui.launcher;

import android.content.Context;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @ClassName: LauncherHolder
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/12 17:18
 * @Version: 1.0
 */
public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
