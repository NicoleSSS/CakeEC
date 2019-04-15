package com.mumu.cake.ui.scanner;

import android.content.Context;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * @ClassName: ScanView
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/26 15:21
 * @Version: 1.0
 */
public class ScanView extends ZBarScannerView {

    public ScanView(Context context) {
        this(context, null);
    }

    public ScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected IViewFinder createViewFinderView(Context context) {
        return new CakeViewFinderView(context);
    }
}
