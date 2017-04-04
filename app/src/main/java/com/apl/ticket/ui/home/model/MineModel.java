package com.apl.ticket.ui.home.model;

import com.apl.ticket.R;
import com.apl.ticket.been.mine.MineInfoBean;
import com.apl.ticket.ui.home.contract.MineContract;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


/**
 * Created by Administrator on 2017/3/29.
 */

public class MineModel implements MineContract.Model {

    @Override
    public Observable<MineInfoBean> getMineInfoBean() {
        List<MineInfoBean>mineInfoList=new ArrayList<>();

        MineInfoBean logIn = new MineInfoBean();
        logIn.setType(0);
        mineInfoList.add(logIn);

        MineInfoBean mineInfo = new MineInfoBean();
        mineInfo.setType(1);
        mineInfoList.add(mineInfo);

        MineInfoBean hobby = new MineInfoBean();
        hobby.setType(2);
        mineInfoList.add(hobby);


        MineInfoBean setting = new MineInfoBean();
        List<String>settingName=new ArrayList<>();
        List<Integer>settingImageUrl=new ArrayList<>();
        settingName.add("设置");
        int mine_icon_settings = R.mipmap.mine_icon_settings;
        settingImageUrl.add(mine_icon_settings);
        setting.setName(settingName);
        setting.setImageUrl(settingImageUrl);
        setting.setType(3);
        mineInfoList.add(setting);

        MineInfoBean share= new MineInfoBean();
        List<String>shareName=new ArrayList<>();
        List<Integer>shareImageUrl=new ArrayList<>();
        shareName.add("分享");
        int mine_icon_share = R.mipmap.mine_icon_share;
        shareImageUrl.add(mine_icon_share);
        share.setName(shareName);
        share.setImageUrl(shareImageUrl);
        share.setType(3);
        mineInfoList.add(share);

        Observable<MineInfoBean> observable=Observable.from(mineInfoList);


        return observable.compose(new AndroidIOToMain.IOToMainTransformer<MineInfoBean>());
    }
}
