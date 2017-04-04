package com.apl.ticket.been;

import java.util.List;



public class SplashBeen {

    private String retdesc;
    private List<SData> startupList;

    public String getRetdesc() {
        return retdesc;
    }

    public List<SData> getStartupList() {
        return startupList;
    }

    public static class SData {
        private String second;
        private String picSmallPath;
        private String name;
        private String picLargePath;


        public String getSecond() {
            return second;
        }

        public String getPicSmallPath() {
            return picSmallPath;
        }

        public String getName() {
            return name;
        }

        public String getPicLargePath() {
            return picLargePath;
        }
    }

}
