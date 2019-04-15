package com.mumu.cake.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @ClassName: ReleaseOpenHelper
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/13 13:58
 * @Version: 1.0
 */
public class ReleaseOpenHelper extends  DaoMaster.OpenHelper{

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
