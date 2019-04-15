package com.mumu.cake.ec.main.personal;

import android.os.Bundle;
import android.view.View;

import com.mumu.cake.delegates.bottom.BottomItemDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.R2;
import com.mumu.cake.ec.main.personal.address.AddressDelegate;
import com.mumu.cake.ec.main.personal.list.ListAdapter;
import com.mumu.cake.ec.main.personal.list.ListBean;
import com.mumu.cake.ec.main.personal.list.ListItemType;
import com.mumu.cake.ec.main.personal.order.OrderListDelegate;
import com.mumu.cake.ec.main.personal.profile.UserProfileDelegate;
import com.mumu.cake.ec.main.personal.settings.SettingsDelegate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: PersonalDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/23 13:08
 * @Version: 1.0
 */
public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @OnClick(R2.id.tv_all_order)
    void onClickOrder(){
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    private void startOrderListByType(){
        OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar(){
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

        ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();

        ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        /**设置RecyclerView*/
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));

    }

    @Override
    public void post(Runnable runnable) {

    }
}
