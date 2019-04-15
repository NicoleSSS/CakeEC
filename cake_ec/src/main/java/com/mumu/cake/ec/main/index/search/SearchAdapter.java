package com.mumu.cake.ec.main.index.search;

import com.mumu.cake.ec.R;
import com.mumu.cake.ui.recycler.MultipleFields;
import com.mumu.cake.ui.recycler.MultipleItemEntity;
import com.mumu.cake.ui.recycler.MultipleRecyclerAdapter;
import com.mumu.cake.ui.recycler.MultipleViewHolder;

import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @ClassName: SearchAdapter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/26 17:46
 * @Version: 1.0
 */
public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()){
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
