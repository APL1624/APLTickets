package com.apl.ticket.been;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class CinemaClassifyBeen {

    private String retcode;

    private String updateTime;

    private List<District> districtList;

    private List<CinemaDistrictRel> cinemaDistrictRelList;

    private List<Circle> circleList;

    private List<CinemaCircleRel> cinemaCircleRelList;

    private List<Subway> subwayList;

    private List<CinemaSubwayRel> cinemaSubwayRelList;

    private List<City> cityList;

    private String appSharedId;

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<District> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }

    public List<CinemaDistrictRel> getCinemaDistrictRelList() {
        return cinemaDistrictRelList;
    }

    public void setCinemaDistrictRelList(List<CinemaDistrictRel> cinemaDistrictRelList) {
        this.cinemaDistrictRelList = cinemaDistrictRelList;
    }

    public List<Circle> getCircleList() {
        return circleList;
    }

    public void setCircleList(List<Circle> circleList) {
        this.circleList = circleList;
    }

    public List<CinemaCircleRel> getCinemaCircleRelList() {
        return cinemaCircleRelList;
    }

    public void setCinemaCircleRelList(List<CinemaCircleRel> cinemaCircleRelList) {
        this.cinemaCircleRelList = cinemaCircleRelList;
    }

    public List<Subway> getSubwayList() {
        return subwayList;
    }

    public void setSubwayList(List<Subway> subwayList) {
        this.subwayList = subwayList;
    }

    public List<CinemaSubwayRel> getCinemaSubwayRelList() {
        return cinemaSubwayRelList;
    }

    public void setCinemaSubwayRelList(List<CinemaSubwayRel> cinemaSubwayRelList) {
        this.cinemaSubwayRelList = cinemaSubwayRelList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public String getAppSharedId() {
        return appSharedId;
    }

    public void setAppSharedId(String appSharedId) {
        this.appSharedId = appSharedId;
    }

    //---------城市----------
    public static class City{

        private String name;

        private String spell;

        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpell() {
            return spell;
        }

        public void setSpell(String spell) {
            this.spell = spell;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }




    //---------地铁-------------
    public static class CinemaSubwayRel{

        private String subwayId;

        private String stationId;

        private String cinemaId;

        public String getSubwayId() {
            return subwayId;
        }

        public void setSubwayId(String subwayId) {
            this.subwayId = subwayId;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(String cinemaId) {
            this.cinemaId = cinemaId;
        }
    }

    public static class Subway{

        private String id;

        private String name;

        private String cityId;

        private String cinemaCount;

        private String stationCount;

        private String groupbuyCount;

        private String weight;

        private String createTime;

        private String updateTime;

        private List<Station> stationList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCinemaCount() {
            return cinemaCount;
        }

        public void setCinemaCount(String cinemaCount) {
            this.cinemaCount = cinemaCount;
        }

        public String getStationCount() {
            return stationCount;
        }

        public void setStationCount(String stationCount) {
            this.stationCount = stationCount;
        }

        public String getGroupbuyCount() {
            return groupbuyCount;
        }

        public void setGroupbuyCount(String groupbuyCount) {
            this.groupbuyCount = groupbuyCount;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<Station> getStationList() {
            return stationList;
        }

        public void setStationList(List<Station> stationList) {
            this.stationList = stationList;
        }
    }


    public static class Station{

        private String id;

        private String subwayId;

        private String name;

        private String cinemaCount;

        private String groupbuyCount;

        private String latitude;

        private String longitude;

        private String orderNum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubwayId() {
            return subwayId;
        }

        public void setSubwayId(String subwayId) {
            this.subwayId = subwayId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCinemaCount() {
            return cinemaCount;
        }

        public void setCinemaCount(String cinemaCount) {
            this.cinemaCount = cinemaCount;
        }

        public String getGroupbuyCount() {
            return groupbuyCount;
        }

        public void setGroupbuyCount(String groupbuyCount) {
            this.groupbuyCount = groupbuyCount;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }
    }



    //------------商圈------------
    public static class CinemaCircleRel{

        private String bizCircleId;

        private String cinemaId;

        public String getBizCircleId() {
            return bizCircleId;
        }

        public void setBizCircleId(String bizCircleId) {
            this.bizCircleId = bizCircleId;
        }

        public String getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(String cinemaId) {
            this.cinemaId = cinemaId;
        }
    }

    public static class Circle{

        private String id;

        private String name;

        private String spell;

        private String districtId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpell() {
            return spell;
        }

        public void setSpell(String spell) {
            this.spell = spell;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }
    }

    //---------------地区------------------------------
    public static class CinemaDistrictRel{

        private String districtId;

        private String cinemaId;

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(String cinemaId) {
            this.cinemaId = cinemaId;
        }
    }

    public static class District{

        private String id;

        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
