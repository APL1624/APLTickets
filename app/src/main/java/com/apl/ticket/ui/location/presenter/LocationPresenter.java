package com.apl.ticket.ui.location.presenter;

import android.util.Log;

import com.apl.ticket.been.location.LocationCityBean;
import com.apl.ticket.ui.location.contract.LocationContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Subscriber;


/**
 * fqc,cry,cry
 */

public class LocationPresenter extends LocationContract.Presenter {

    private static final String TAG = LocationPresenter.class.getSimpleName();

    @Override
    public void handleModelToView() {
        mModel.getCityList(mView.setModelKey()).subscribe(new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "error");
            }

            @Override
            public void onNext(String s) {
                List<LocationCityBean> data = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray result1 = jsonObject.getJSONArray("result");
                    for (int k = 0; k < result1.length()-1; k++) {

                        JSONArray jsonArray = result1.getJSONArray(k);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            LocationCityBean city = new LocationCityBean();
                            city.setId(jsonObject1.getString("id"));
                            city.setFullName(jsonObject1.getString("fullname"));
                            JSONArray pinyin = jsonObject1.getJSONArray("pinyin");
                            String firstName = new String();
                            for (int j = 0; j < pinyin.length(); j++) {
                                firstName += pinyin.getString(j);
                            }
                            city.setFirstName(firstName);
                            filterData(data,city);
                        }
                    }

                    data = setDataType(data);
                    Log.e(TAG, data.get(1).getType() + data.get(1).getFullName());
                    mView.showLocationData(data);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void filterData(List<LocationCityBean> data, LocationCityBean city) {
        String id = city.getId();
        if (id.length() > 4) {
            String substring = id.substring(id.length() - 4);
            if (substring.equals("0000")) {
                if (id.equals("110000") || id.equals("120000") || id.equals("310000")
                        || id.equals("500000")) {
                    data.add(city);
                }
            } else {
                String substring1 = id.substring(id.length() - 2);
                if (substring1.equals("00")) {
                    data.add(city);

                }
            }
        }

    }

    public List<LocationCityBean> setDataType(List<LocationCityBean> data) {

        List<LocationCityBean> realData = new ArrayList<>();
        LocationCityBean locationCityBean1 = new LocationCityBean();
        locationCityBean1.setFullName("热门城市");
        locationCityBean1.setType(0);
        realData.add(locationCityBean1);
        for (int i = 0; i < data.size(); i++) {
            LocationCityBean locationCityBean = data.get(i);
            String id = locationCityBean.getId();
            String substring1 = id.substring(id.length() - 4, id.length() - 2);
            if (substring1.equals("00") || substring1.equals("01")) {
                realData.add(locationCityBean);
            }
            data.get(i).setType(1);
        }
        Collections.sort(data, new Comparator<LocationCityBean>() {
            @Override
            public int compare(LocationCityBean locationCityBean, LocationCityBean t1) {
                return locationCityBean.getFirstName().compareTo(t1.getFirstName());
            }
        });
        for (int i = 0; i < data.size(); i++) {
            if (i==0) {
                locationCityBean1 = new LocationCityBean();
                locationCityBean1.setFullName(data.get(i).getFirstName().substring(0,1).toUpperCase());
                locationCityBean1.setType(0);
                realData.add(locationCityBean1);

            }else {
                LocationCityBean locationCityBean = data.get(i);
                LocationCityBean locationCityBean2 = data.get(i -1);
                if (!locationCityBean.getFirstName().substring(0,1).equals(locationCityBean2.getFirstName().substring(0,1))) {
                    locationCityBean1 = new LocationCityBean();
                    locationCityBean1.setFullName(data.get(i).getFirstName().substring(0,1).toUpperCase());
                    locationCityBean1.setType(0);
                    realData.add(locationCityBean1);
                }

            }
            realData.add(data.get(i));

        }

        return realData;
    }
}
