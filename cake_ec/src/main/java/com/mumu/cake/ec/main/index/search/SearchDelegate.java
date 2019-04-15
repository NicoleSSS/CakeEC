package com.mumu.cake.ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.R2;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.ui.recycler.DividerLookupImpl;
import com.mumu.cake.ui.recycler.MultipleItemEntity;
import com.mumu.cake.util.storage.CakePreference;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: SearchDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/26 17:33
 * @Version: 1.0
 */
public class SearchDelegate extends CakeDelegate {

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit = null;

    @OnClick(R2.id.tv_top_search)
    void onCliclSearch() {
        RestClient.builder()
                .url("search.php?key=")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String searchItemText = mSearchEdit.getText().toString();
                        saveItem(searchItemText);
                        mSearchEdit.setText("");
                        /**处理数据：展示一些东西，或者弹出一段话*/
                    }
                })
                .build()
                .get();
    }

    @OnClick(R2.id.icon_top_search_back)
    void onClickBack() {
        getSupportDelegate().pop();
    }

    @SuppressWarnings("unchecked")
    private void saveItem(String item) {
        if(!StringUtils.isEmpty(item) && !StringUtils.isSpace(item)){
            List<String> history;
            final String historyStr = CakePreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
            if(StringUtils.isEmpty(historyStr)){
                history = new ArrayList<>();
            }else {
                history = JSON.parseObject(historyStr,ArrayList.class);
            }

            history.add(item);
            final String json = JSON.toJSONString(history);

            CakePreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, json);
        }
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);

        final List<MultipleItemEntity> data = new SearchDataConverter().convert();
        final SearchAdapter adapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(adapter);

        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20, 20)
                        .color(Color.GRAY)
                        .build();
            }
        });

        mRecyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }

    @Override
    public void post(Runnable runnable) {

    }
}
