package com.mumu.cake.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;


import com.mumu.cake.ec.R;
import com.mumu.cake.ui.recycler.RgbValue;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * @ClassName: TranslucentBehavior
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/16 22:51
 * @Version: 1.0
 */
@SuppressWarnings("unchecked")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    /**顶部距离*/
    private int mDistanceY = 0;
    /**颜色变化速度*/
    private static final int SHOW_SPEED = 3;
    /**定义变化的颜色*/
    private final RgbValue RGB_AVLUE = RgbValue.create(255, 124, 2);

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Toolbar child, @NonNull View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        /**增加滑动距离*/
        mDistanceY += dy;
        /**获得toolbar的高度*/
        final int targetHeight = child.getBottom();
        /**当滑动时，并且距离小于toolbar高度的时候，调整渐变色*/
        if(mDistanceY > 0 && mDistanceY <= targetHeight){
            final float scale = (float)mDistanceY / targetHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int)alpha, RGB_AVLUE.red(), RGB_AVLUE.green(), RGB_AVLUE.blue()));
        }else if(mDistanceY > targetHeight){
            child.setBackgroundColor(Color.rgb(RGB_AVLUE.red(),RGB_AVLUE.green(),RGB_AVLUE.blue()));
        }
    }
}
