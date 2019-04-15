package com.mumu.cake.ec.main.personal.profile;

import android.os.Bundle;
import android.view.View;

import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.R2;
import com.mumu.cake.ec.main.personal.list.ListAdapter;
import com.mumu.cake.ec.main.personal.list.ListBean;
import com.mumu.cake.ec.main.personal.list.ListItemType;
import com.mumu.cake.ec.main.personal.settings.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @ClassName: UserProfileDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/24 18:22
 * @Version: 1.0
 */
public class UserProfileDelegate extends CakeDelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553434853878&di=a871d3db26a62c684b63138e07200b57&imgtype=0&src=http%3A%2F%2Fsoomal.com%2Fimages%2Fdoc%2F20170423%2F00067580.jpg")
                .build();

        ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setDelegate(new NameDelegate())
                .setValue("未设置姓名")
                .build();

        ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置性别")
                .build();

        ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置生日名")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        /**设置RecyclerView*/
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }

    @Override
    public void post(Runnable runnable) {

    }
}
