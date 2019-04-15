package com.mumu.cake.ec.main.personal.profile;


import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.mumu.cake.app.Cake;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.main.personal.list.ListBean;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.ui.date.DateDialogUtil;
import com.mumu.cake.util.callback.CallbackManager;
import com.mumu.cake.util.callback.CallbackType;
import com.mumu.cake.util.callback.IGlobalCallback;
import com.mumu.cake.util.log.CakeLogger;

import androidx.appcompat.app.AlertDialog;

/**
 * @ClassName: UserProfileClickListener
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/24 19:02
 * @Version: 1.0
 */
public class UserProfileClickListener extends SimpleClickListener {

    private final CakeDelegate DELEGATE;
    /**性别*/
    private String[] mGenders = new String[]{"男","女","保密"};

    public UserProfileClickListener(CakeDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id){
            case 1:
                /**开始照相机或选择图片*/
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                            @Override
                            public void executeCallback(Uri args) {
                                CakeLogger.d("ON_CROP",args);
                                final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                                Glide.with(DELEGATE)
                                        .load(args)
                                        .into(avatar);

                                RestClient.builder()
                                        .url("上传图片地址")
                                        .loader(DELEGATE.getContext())
                                        .file(args.getPath())
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccess(String response) {
                                                CakeLogger.d("ON_CROP_UPLOAD",response);
                                                String path = JSON.parseObject(response)
                                                        .getJSONObject("result")
                                                        .getString("path");

                                                /**通知服务器更新信息*/
                                                RestClient.builder()
                                                        .url("user_profile")
                                                        .params("avatar",path)
                                                        .loader(DELEGATE.getContext())
                                                        .success(new ISuccess() {
                                                            @Override
                                                            public void onSuccess(String response) {
                                                                /**
                                                                 * 获取跟新后的用户信息，然后更新本地数据库
                                                                 * 或者没有本地数据的APP，每次打开APP都请求API来获取信息
                                                                 */

                                                            }
                                                        })
                                                        .build()
                                                        .post();
                                            }
                                        })
                                        .build()
                                        .upload();
                            }
                        });
                DELEGATE.startCameraWithCheck();

                break;
            case 2:
                final CakeDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = (TextView)view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView)view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
             default:
                 break;

        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
