package com.mumu.cake.ec.main.personal.order;

import android.os.Bundle;
import android.view.View;

import com.mumu.cake.ec.R2;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.main.personal.PersonalDelegate;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.ui.recycler.MultipleItemEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @ClassName: OrderListDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/23 18:49
 * @Version: 1.0
 */
public class OrderListDelegate extends CakeDelegate {

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("personal_order_list")
                .loader(getContext())
                .params("type",mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));

                    }
                })
                .build()
                .get();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
