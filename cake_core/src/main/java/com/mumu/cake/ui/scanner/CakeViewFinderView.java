package com.mumu.cake.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;
import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * @ClassName: CakeViewFinderView
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/26 15:22
 * @Version: 1.0
 */
public class CakeViewFinderView extends ViewFinderView {

    public CakeViewFinderView(Context context) {
        super(context,null);
    }

    public CakeViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }
}
