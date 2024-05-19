package com.atguigu.travel.component;


import com.atguigu.travel.dao.StationMapper;
import com.atguigu.travel.dao.TravelMapper;
import com.atguigu.model.pojo.Station;
import com.atguigu.model.pojo.Travel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class InfoInit {

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private TravelMapper travelMapper;

    private static HashMap<String, Integer[][]> rectangles;

    public HashMap<String, Station> InitStationsKeyName() {
        List<Station> stations = stationMapper.selectList(null);

        //将得到的所有车站以id为键,对象为值,放入HashMap中
        HashMap<String, Station> allStations = new HashMap<>();
        for (Station st : stations) {
            allStations.put(st.getName(), st);
        }
        return allStations;
    }

    public HashMap<Integer, Station> InitStationsKeyId() {
        List<Station> stations = stationMapper.selectList(null);

        //将得到的所有车站以id为键,对象为值,放入HashMap中
        HashMap<Integer, Station> allStations = new HashMap<>();
        for (Station st : stations) {
            allStations.put(st.getId(), st);
        }
        return allStations;
    }

    public HashMap<String, Integer[][]> InitRectangle() {

        if (rectangles != null) {
            return rectangles;
        }

        LambdaQueryWrapper<Travel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderBy(true, true, Travel::getOrigin)
                .orderBy(true, true, Travel::getDestination);

        List<Travel> travelList = travelMapper.selectList(lambdaQueryWrapper);

        int numOfStation = Math.toIntExact(stationMapper.selectCount(null));

        Integer[][] disRectangle = new Integer[numOfStation + 1][numOfStation + 1];
        Integer[][] moneyRectangle = new Integer[numOfStation + 1][numOfStation + 1];
        Integer[][] timeRectangle = new Integer[numOfStation + 1][numOfStation + 1];

        for (Travel travel : travelList) {
            disRectangle[travel.getOrigin()][travel.getDestination()] = travel.getDistance();
            moneyRectangle[travel.getOrigin()][travel.getDestination()] = travel.getMoney();
            timeRectangle[travel.getOrigin()][travel.getDestination()] = travel.getSpendTime();
        }

        rectangles = new HashMap<>();
        rectangles.put("DistanceRectangle", disRectangle);
        rectangles.put("MoneyRectangle", moneyRectangle);
        rectangles.put("TimeRectangle", timeRectangle);

        return rectangles;
    }
}
