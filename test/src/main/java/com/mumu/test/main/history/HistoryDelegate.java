package com.mumu.test.main.history;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.mumu.cake.delegates.bottom.BottomItemDelegate;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.ui.recycler.MultipleItemEntity;
import com.mumu.test.R;
import com.mumu.test.R2;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @ClassName: HistoryDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/28 18:59
 * @Version: 1.0
 */
public class HistoryDelegate extends BottomItemDelegate {

//    @BindView(R2.id.sp_hospital)
//    NiceSpinner mHospitalSpinner = null;
//    @BindView(R2.id.sp_device)
//    NiceSpinner mDeviceSpinner = null;
//    @BindView(R2.id.sp_start_time)
//    NiceSpinner mStartTimeSpinner = null;
//    @BindView(R2.id.sp_stop_time)
//    NiceSpinner mStopTimeSpinner = null;
    @BindView(R2.id.rv_history)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_history;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

//        List<String> dataset = new LinkedList<>(Arrays.asList("四川华西医院", "成都第一人民医院", "成都第二人民医院", "成都妇女儿童医院", "北京协和医院"));
////        mHospitalSpinner.attachDataSource(dataset);

        RestClient.builder()
                .url("history")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new HistoryDataConverter().setJsonData(response).convert();
                        final HistoryAdapter historyAdapter = new HistoryAdapter(data);
                        mRecyclerView.setAdapter(historyAdapter);
                    }
                })
                .build()
                .get();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
