package com.mumu.test.main.history;

import android.view.View;

import com.mumu.cake.ui.recycler.MultipleFields;
import com.mumu.cake.ui.recycler.MultipleItemEntity;
import com.mumu.cake.ui.recycler.MultipleRecyclerAdapter;
import com.mumu.cake.ui.recycler.MultipleViewHolder;
import com.mumu.test.R;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * @ClassName: HistoryAdapter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/29 21:33
 * @Version: 1.0
 */
public class HistoryAdapter extends MultipleRecyclerAdapter {

    protected HistoryAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(HistoryItemType.ITEM_HISTORY, R.layout.item_history);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case HistoryItemType.ITEM_HISTORY:
                final String state = entity.getField(HistoryItemFields.STATE);

                final int id = entity.getField(MultipleFields.ID);
                final int device_id = entity.getField(HistoryItemFields.DEVICE_ID);
                final int hospital_id = entity.getField(HistoryItemFields.HOSPITAL_ID);

                final int alarm_level = entity.getField(HistoryItemFields.ALARM_LEVEL);
                final String alarm_time = entity.getField(HistoryItemFields.ALARM_TIME);
                final String message = entity.getField(HistoryItemFields.MESSAGE);
                final String device_name = entity.getField(HistoryItemFields.DEVICE_NAME);
                final String hospital = entity.getField(HistoryItemFields.HOSPITAL);
                final String recovery_time = entity.getField(HistoryItemFields.RECOVERY_TIME);
                final String dispose_man = entity.getField(HistoryItemFields.DISPOSE_MAN);

                final LinearLayoutCompat timeLayout = holder.getView(R.id.ll_history_time);
                final LinearLayoutCompat alarmTimeLayout = holder.getView(R.id.ll_history_alarm_time);
                final LinearLayoutCompat recoveryTimeLayout = holder.getView(R.id.ll_history_recovery_time);
                final LinearLayoutCompat stateLayout = holder.getView(R.id.ll_history_state);
                final LinearLayoutCompat disposeManLayout = holder.getView(R.id.ll_history_dispose_man);
                final View divideView = holder.getView(R.id.view_state);

                final AppCompatImageView alarmImage = holder.getView(R.id.image_history_list);
                final AppCompatTextView timeText = holder.getView(R.id.tv_history_time);
                final AppCompatTextView hospitalText = holder.getView(R.id.tv_history_hospital);
                final AppCompatTextView deviceText = holder.getView(R.id.tv_history_device);
                final AppCompatTextView messageText = holder.getView(R.id.tv_history_message);

                final AppCompatTextView alarmTimeText = holder.getView(R.id.tv_history_alarm_time);
                final AppCompatTextView recoveryTimeText = holder.getView(R.id.tv_history_recovery_time);
                final AppCompatTextView stateText = holder.getView(R.id.tv_history_state);
                final AppCompatTextView disposeManText = holder.getView(R.id.tv_history_dispose_man);

                if(state.equals("未处理")){
                    switch (alarm_level){
                        case 1:
                            alarmImage.setImageResource(R.drawable.first_grade);
                            break;
                        case 2:
                            alarmImage.setImageResource(R.drawable.second_grade);
                            break;
                        case 3:
                            alarmImage.setImageResource(R.drawable.third_grade);
                            break;
                        case 4:
                            alarmImage.setImageResource(R.drawable.forth_grade);
                            break;
                        default:
                            break;
                    }
                    timeText.setText(alarm_time);
                    alarmTimeLayout.setVisibility(View.GONE);
                    recoveryTimeLayout.setVisibility(View.GONE);
                    stateLayout.setVisibility(View.GONE);
                    disposeManLayout.setVisibility(View.GONE);
                    divideView.setVisibility(View.GONE);
                }else{
                    switch (alarm_level){
                        case 1:
                            alarmImage.setImageResource(R.drawable.first_grade_grey);
                            break;
                        case 2:
                            alarmImage.setImageResource(R.drawable.second_grade_grey);
                            break;
                        case 3:
                            alarmImage.setImageResource(R.drawable.third_grade_grey);
                            break;
                        case 4:
                            alarmImage.setImageResource(R.drawable.forth_grade_grey);
                            break;
                        default:
                            break;
                    }

                    alarmTimeText.setText(alarm_time);
                    recoveryTimeText.setText(recovery_time);
                    disposeManText.setText(dispose_man);
                    stateText.setText(state);
                    timeLayout.setVisibility(View.GONE);
                }
                hospitalText.setText(hospital);
                deviceText.setText(device_name);
                messageText.setText(message);

                break;
            default:
                break;
        }
    }
}
