package com.apl.ticket.been;

import java.util.List;

/**
 * 首页详情
 */

public class HomeDetailBeen {
    private String retdesc;
    private HDPData object;

    public String getRetdesc() {
        return retdesc;
    }

    public HDPData getObject() {
        return object;
    }



    public static class HDPData {
        private String id;
        private String name;
        private String highlight;
        private String grade;
        private String logo;
        private String isNew;
        private String isSale;
        private String isDiscount;
        private String dimensional;
        private int isSeatOccupy;
        private String releaseDate;
        private String description;
        private String duration;
        private String director;
        private String actors;
        private String logo1;
        private String logo2;
        private String logo3;
        private String logo556640;
        private String category;
        private String area;
        private String language;
        private String isHot;
        private String preview;
        private String timeList;
        private String notifyCount;
        private String onShowStatus;
        private String lowPrice;
        private String logo180320;
        private String logo520692;
        private String highBoxOffice;
        private List<HDPDLogo> stillsList;
        private String mobilePreview;
        private String isAvailableInCurrentCity;
        private String isScheduleSupport;
        private String screenings;
        private String spell;
        private String musicId;


        public String getMusicId() {
            return musicId;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getHighlight() {
            return highlight;
        }

        public String getGrade() {
            return grade;
        }

        public String getLogo() {
            return logo;
        }

        public String getIsNew() {
            return isNew;
        }

        public String getIsSale() {
            return isSale;
        }

        public String getIsDiscount() {
            return isDiscount;
        }

        public String getDimensional() {
            return dimensional;
        }

        public int getIsSeatOccupy() {
            return isSeatOccupy;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getDescription() {
            return description;
        }

        public String getDuration() {
            return duration;
        }

        public String getDirector() {
            return director;
        }

        public String getActors() {
            return actors;
        }

        public String getLogo1() {
            return logo1;
        }

        public String getLogo2() {
            return logo2;
        }

        public String getLogo3() {
            return logo3;
        }

        public String getLogo556640() {
            return logo556640;
        }

        public String getCategory() {
            return category;
        }

        public String getArea() {
            return area;
        }

        public String getLanguage() {
            return language;
        }

        public String getIsHot() {
            return isHot;
        }

        public String getPreview() {
            return preview;
        }

        public String getTimeList() {
            return timeList;
        }

        public String getNotifyCount() {
            return notifyCount;
        }

        public String getOnShowStatus() {
            return onShowStatus;
        }

        public String getLowPrice() {
            return lowPrice;
        }

        public String getLogo180320() {
            return logo180320;
        }

        public String getLogo520692() {
            return logo520692;
        }

        public String getHighBoxOffice() {
            return highBoxOffice;
        }

        public List<HDPDLogo> getStillsList() {
            return stillsList;
        }

        public String getMobilePreview() {
            return mobilePreview;
        }

        public String getIsAvailableInCurrentCity() {
            return isAvailableInCurrentCity;
        }

        public String getIsScheduleSupport() {
            return isScheduleSupport;
        }

        public String getScreenings() {
            return screenings;
        }

        public String getSpell() {
            return spell;
        }

        public static class HDPDLogo {
            private String logo;
            private String logo1;

            public String getLogo() {
                return logo;
            }

            public String getLogo1() {
                return logo1;
            }
        }
    }
}
