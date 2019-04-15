package com.mumu.cake.delegates.bottom;

import android.widget.Toast;

import com.mumu.cake.R;
import com.mumu.cake.delegates.CakeDelegate;

/**
 * @ClassName: BottomItemDelegate
 * @Description: 每个页面，需要进行监听Key事件
 * @Author: 范琳琳
 * @CreateDate: 2019/3/14 20:49
 * @Version: 1.0
 */
public abstract class BottomItemDelegate extends CakeDelegate {

    /**再点一次退出程序时间设置*/
    private long TOUCH_TIME = 0;
    private static final long WAIT_TIME = 2000L;

    @Override
    public boolean onBackPressedSupport() {
        if(System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME){
            _mActivity.finish();
        }else{
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
