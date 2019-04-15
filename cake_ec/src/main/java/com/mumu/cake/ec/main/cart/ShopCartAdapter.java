package com.mumu.cake.ec.main.cart;

import android.graphics.Color;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.mumu.cake.app.Cake;
import com.mumu.cake.ec.R;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.ui.recycler.MultipleFields;
import com.mumu.cake.ui.recycler.MultipleItemEntity;
import com.mumu.cake.ui.recycler.MultipleRecyclerAdapter;
import com.mumu.cake.ui.recycler.MultipleViewHolder;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

/**
 * @ClassName: ShopCartAdapter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/22 11:23
 * @Version: 1.0
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectAll = false;
    private ICartItemListener mCartItemListener = null;
    private double mTotalPrice = 0.0;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        /**初始化总价*/
        for(MultipleItemEntity entity: data){
            final double price = entity.getField(ShopCartFields.PRICE);
            final int  count = entity.getField(ShopCartFields.COUNT);
            final double total = price * count;
            mTotalPrice += total;
        }
        /**添加购物车item布局*/
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setIsSelectAll(boolean isSelectAll) {
        this.mIsSelectAll = isSelectAll;
    }

    public void setCartItemListener(ICartItemListener listener) {
        this.mCartItemListener = listener;
    }

    public double getTotalPrice( ) {
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                /**取出所有值*/
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartFields.TITLE);
                final String desc = entity.getField(ShopCartFields.DESC);
                final int count = entity.getField(ShopCartFields.COUNT);
                final double price = entity.getField(ShopCartFields.PRICE);

                /**取出所有控件*/
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                /**赋值*/
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .into(imgThumb);

                /**在左侧选中渲染之前改变全选与否状态*/
                entity.setField(ShopCartFields.IS_SELECTED, mIsSelectAll);
                final boolean isSelected = entity.getField(ShopCartFields.IS_SELECTED);

                /**根据状态显示左侧选中*/
                if (isSelected) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Cake.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                /**添加左侧选中点击事件*/
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(Cake.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartFields.IS_SELECTED, true);
                        }
                    }
                });
                /**添加加减事件*/
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            /**实时同步数据*/
                            RestClient.builder()
                                    .url("shop_cart_count")
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum --;
                                            tvCount.setText(String.valueOf(countNum));
                                            if(mCartItemListener != null){
                                                mTotalPrice -= price;
                                                final double itemTotal = countNum * price;
                                                mCartItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            /**实时同步数据*/
                            RestClient.builder()
                                    .url("shop_cart_count")
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum ++;
                                            tvCount.setText(String.valueOf(countNum));
                                            if(mCartItemListener != null){
                                                mTotalPrice += price;
                                                final double itemTotal = countNum * price;
                                                mCartItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
