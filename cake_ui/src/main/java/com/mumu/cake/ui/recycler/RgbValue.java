package com.mumu.cake.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * @ClassName: RgbValue
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/17 15:58
 * @Version: 1.0
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue){
        return new AutoValue_RgbValue(red, green, blue);
    }
}
