package com.mumu.cakeec.example.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mob.MobSDK;
import com.mumu.cake.delegates.web.event.Event;
import com.mumu.cake.util.log.CakeLogger;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * @ClassName: ShareEvent
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/21 16:32
 * @Version: 1.0
 */
public class ShareEvent extends Event {

    @Override
    public String execute(String params) {
        CakeLogger.json("ShareEvent", params);

        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

        MobSDK.init(getContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(title);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(imageUrl);//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(url);
        // 启动分享GUI
        oks.show(getContext());
        return null;
    }


}
