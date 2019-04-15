package com.mumu.cake.ec.main.personal.address;

import android.view.View;

import com.mumu.cake.ec.R;
import com.mumu.cake.net.RestClient;
import com.mumu.cake.net.callback.ISuccess;
import com.mumu.cake.ui.recycler.MultipleFields;
import com.mumu.cake.ui.recycler.MultipleItemEntity;
import com.mumu.cake.ui.recycler.MultipleRecyclerAdapter;
import com.mumu.cake.ui.recycler.MultipleViewHolder;

import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @ClassName: AddressAdapter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/25 17:49
 * @Version: 1.0
 */
public class AddressAdapter extends MultipleRecyclerAdapter {

    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case AddressItemType.ITEM_ADDRESS:
                final String name = entity.getField(MultipleFields.NAME);
                final String phone = entity.getField(AddressItemFields.PHONE);
                final String address = entity.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getField(MultipleFields.TAG);
                final int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /**点击删除时，删除收获地址*/
//                        RestClient.builder()
//                                .url("address")
//                                .params("id",id)
//                                .success(new ISuccess() {
//                                    @Override
//                                    public void onSuccess(String response) {
//                                        remove(holder.getLayoutPosition());
//                                    }
//                                })
//                                .build()
//                                .post();
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;
        }
    }
}
