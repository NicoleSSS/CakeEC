package com.mumu.cake.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.R2;
import com.mumu.cake.ui.widget.AutoPhotoLayout;
import com.mumu.cake.ui.widget.StarLayout;
import com.mumu.cake.util.callback.CallbackManager;
import com.mumu.cake.util.callback.CallbackType;
import com.mumu.cake.util.callback.IGlobalCallback;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: OrderCommentDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/25 22:19
 * @Version: 1.0
 */
public class OrderCommentDelegate extends CakeDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit(){
        Toast.makeText(getContext(), "评分："+mStarLayout.getStarCount(), Toast.LENGTH_LONG).show();
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@NonNull Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);
                    }
                });
    }

    @Override
    public void post(Runnable runnable) {

    }
}
