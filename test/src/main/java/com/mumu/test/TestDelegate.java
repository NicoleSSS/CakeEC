package com.mumu.test;

import com.mumu.cake.delegates.bottom.image.BaseImageBottomDelegate;
import com.mumu.cake.delegates.bottom.BottomItemDelegate;
import com.mumu.cake.delegates.bottom.image.ImageBottomTabBean;
import com.mumu.cake.delegates.bottom.image.ImageItemBuilder;
import com.mumu.test.main.history.HistoryDelegate;
import com.mumu.test.main.MeDelegate;

import java.util.LinkedHashMap;

/**
 * @ClassName: TestDelegate
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/28 18:56
 * @Version: 1.0
 */
public class TestDelegate extends BaseImageBottomDelegate {

    @Override
    public LinkedHashMap<ImageBottomTabBean, BottomItemDelegate> setItems(ImageItemBuilder builder) {

        final LinkedHashMap<ImageBottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();

        items.put(new ImageBottomTabBean(R.drawable.alarm_processing_grey, R.drawable.alarm_processing_blue, "报警处理"), new HistoryDelegate());
        items.put(new ImageBottomTabBean(R.drawable.me_grey, R.drawable.me_blue,"我的"),new MeDelegate());

        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return getResources().getColor(R.color.app_main);
    }

    @Override
    public void post(Runnable runnable) {

    }
}
