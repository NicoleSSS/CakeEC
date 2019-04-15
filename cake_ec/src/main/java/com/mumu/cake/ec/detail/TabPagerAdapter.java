package com.mumu.cake.ec.detail;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @ClassName: TabPagerAdapter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/26 22:53
 * @Version: 1.0
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<String> TAB_TITLES = new ArrayList<>();
    private final ArrayList<ArrayList<String>> PICTURES = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, JSONObject data) {
        super(fm);
        /**获取Tabs的信息，注意这里的tabs是一条信息*/
        final JSONArray tagbs = data.getJSONArray("tabs");
        final int size = tagbs.size();
        for(int i= 0; i < size;i++){
            final JSONObject eachTab = tagbs.getJSONObject(i);
            final String name = eachTab.getString("name");
            final JSONArray pictureUrls = eachTab.getJSONArray("pictures");
            final ArrayList<String> eachTabPicturesArray = new ArrayList<>();
            /**存储每个图片*/
            final int pictureSize = pictureUrls.size();
            for(int j = 0; j < pictureSize; j++){
                eachTabPicturesArray.add(pictureUrls.getString(j));
            }
            TAB_TITLES.add(name);
            PICTURES.add(eachTabPicturesArray);

        }
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return ImageDelegate.create(PICTURES.get(0));
        }else{
            return ImageDelegate.create(PICTURES.get(1));
        }
    }

    @Override
    public int getCount() {
        return TAB_TITLES.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }
}
