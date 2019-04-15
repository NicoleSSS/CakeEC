package com.mumu.cake.ec.main.sort.list;

import android.graphics.Color;
import android.view.View;

import com.mumu.cake.delegates.CakeDelegate;
import com.mumu.cake.ec.R;
import com.mumu.cake.ec.main.sort.SortDelegate;
import com.mumu.cake.ec.main.sort.content.ContentDelegate;
import com.mumu.cake.ui.recycler.ItemType;
import com.mumu.cake.ui.recycler.MultipleFields;
import com.mumu.cake.ui.recycler.MultipleItemEntity;
import com.mumu.cake.ui.recycler.MultipleRecyclerAdapter;
import com.mumu.cake.ui.recycler.MultipleViewHolder;

import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import me.yokeyword.fragmentation.SupportHelper;

/**
 * @ClassName: SortRecyclerAdapter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/20 16:51
 * @Version: 1.0
 */
public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    /**上一个选中item的position*/
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        /**添加垂直菜单布局*/
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if(mPrePosition != currentPosition){
                            /**还原上一个*/
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);
                            /**更新选中的item*/
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                if(!isClicked){
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                }else{
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:break;
        }
    }

    private void showContent(int contentId){
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }

    private void switchContent(ContentDelegate delegate){
        final CakeDelegate contentDelegate =
                SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if(contentDelegate != null){
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);

        }
    }
}