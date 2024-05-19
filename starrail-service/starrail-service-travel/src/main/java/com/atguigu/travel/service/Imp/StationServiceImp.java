package com.atguigu.travel.service.Imp;


import com.atguigu.model.pojo.Station;
import com.atguigu.travel.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service("stationService")
@Transactional
public class StationServiceImp implements StationService {

    @Autowired
    @Qualifier("allStationsKeyName")
    private HashMap<String, Station> stationsKeyName;

    @Autowired
    @Qualifier("allStationsKeyId")
    private HashMap<Integer, Station> stationsKeyId;


    public int getStationNum(){
        return stationsKeyId.size();
    }

    @Override
    public int getStationIdByName(String stationName) {
        return stationsKeyName.get(stationName).getId();
    }

    @Override
    public String getStationNameById(int stationId) {
        return stationsKeyId.get(stationId).getName();
    }

    @Override
    public boolean stationsAllExist(String begin, String destination) {
        Station beginStation = stationsKeyName.get(begin);
        Station destinationStation = stationsKeyName.get(destination);

        // 如果有一个站点不存在，就返回false;
        if (begin == null || destinationStation == null){
            return false;
        }

        return true;
    }


}
