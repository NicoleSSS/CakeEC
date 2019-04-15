package com.mumu.cake.ec.main.personal.address;

import android.os.Bundle;
import android.view.View;

import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.R2;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.ui.recycler.MultipleItemEntity;
import com.mumu.cake.util.log.CakeLogger;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @ClassName: AddressDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/25 17:59
 * @Version: 1.0
 */
public class AddressDelegate extends CakeDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("address")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onSuccess(String response) {
        CakeLogger.d("AddressDelegate",response);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
