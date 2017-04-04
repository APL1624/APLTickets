package com.apl.ticket.been;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class FoundImageBeen {

    private String retcode;

    private List<Banner> bannerList;

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public List<Banner> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }

    public static class Banner implements Parcelable{

        private String name;

        private String picLargePath;

        private String url;

        protected Banner(Parcel in) {
            name = in.readString();
            picLargePath = in.readString();
            url = in.readString();
        }

        public static final Creator<Banner> CREATOR = new Creator<Banner>() {
            @Override
            public Banner createFromParcel(Parcel in) {
                return new Banner(in);
            }

            @Override
            public Banner[] newArray(int size) {
                return new Banner[size];
            }
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicLargePath() {
            return picLargePath;
        }

        public void setPicLargePath(String picLargePath) {
            this.picLargePath = picLargePath;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeString(picLargePath);
            parcel.writeString(url);
        }
    }
}
