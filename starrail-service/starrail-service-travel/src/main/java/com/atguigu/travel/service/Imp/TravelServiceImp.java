package com.atguigu.travel.service.Imp;


import com.atguigu.model.dto.PathSelectDTO;
import com.atguigu.model.pojo.Ticket;
import com.atguigu.travel.service.StationService;
import com.atguigu.travel.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("travelService")
@Transactional
public class TravelServiceImp implements TravelService {

    @Resource
    private RedisTemplate<String ,String> redisTemplate;

    @Autowired
    private StationService stationService;

    @Autowired
    @Qualifier("DistanceRectangle")
    private Integer[][] DistanceRectangle;

    @Autowired
    @Qualifier("MoneyRectangle")
    private Integer[][] MoneyRectangle;

    @Autowired
    @Qualifier("TimeRectangle")
    private Integer[][] TimeRectangle;


    // 获取生成随机车票所需要的车票因子
    @Override
    @Cacheable(value = "ticketFactor",key = "#dto.startStation + #dto.destination + #dto.startDate")
    @CachePut(value = "ticketFactor",key = "#dto.startStation + #dto.destination + #dto.startDate")
    public List<Ticket> getTicketFactor(PathSelectDTO dto) {

        LocalDate startDate = LocalDate.parse(dto.getStartDate());
        String startStation = dto.getStartStation();
        String destinationStation = dto.getDestination();

        String[] types = {"最少花费","最短时间","最短路程"};
        List<Ticket> ticketFactors = new ArrayList<>(3);

        // 获取到三种类型的车票因子
        for(String type : types){

            int[] pathArray = getPathArray(type, startStation, destinationStation);
            int distance = getTotalLenByPath(pathArray);
            int price = getTotalMoneyByPath(pathArray);


            int time = getTotalTimeByPath(pathArray);
            LocalTime spendTime = LocalTime.of(time/60, time%60);

            String pathInfo = getPathStationInfo(pathArray);
            LocalDateTime startTime = LocalDateTime.of(startDate, LocalTime.of(0, 0));

            Ticket randomTicketFactor = new Ticket(startStation, destinationStation,startTime, distance, spendTime, price, pathInfo);
            ticketFactors.add(randomTicketFactor);
        }

        return ticketFactors;
    }


    //获取存储路过站点编号的数组
    public int[] getPathArray(String type, String beginStation, String destinationStation) {

        int numOfStations = stationService.getStationNum();
        int startStationId = stationService.getStationIdByName(beginStation);
        int destinationStationId = stationService.getStationIdByName(destinationStation);

        switch (type) {
            case "最短路程":
                return minRes(startStationId, destinationStationId, DistanceRectangle, numOfStations);
            case "最少花费":
                return minRes(startStationId, destinationStationId, MoneyRectangle, numOfStations);
            case "最短时间":
                return minRes(startStationId, destinationStationId, TimeRectangle, numOfStations);
        }
        return null;
    }














    // 获取所有经过的车站的名称
    @Override
    public String getPathStationInfo(int[] pathArray) {
        int stationNum = pathArray.length - 1;
        StringBuilder result = new StringBuilder();

        for(int i = 0;i < stationNum;i++ ){
            String stationName = stationService.getStationNameById(pathArray[i]);
            result.append(stationName).append("->");
        }

        // 追加最后一个车站名称
        String lastStationName = stationService.getStationNameById(pathArray[stationNum]);
        result.append(lastStationName);

        return result.toString();
    }




    @Override
    public int[] getPathByArray(int[] frontStation, Integer start, Integer destination) {
        //因为经过的站点数量并不确定，所以使用ArrayList
        ArrayList<Integer> arrayList = new ArrayList();

        //倒数第一个车站，就是终点站的前一个
        int front = frontStation[destination];
        arrayList.add(destination);
        arrayList.add(front);

        while (true) {
            front = frontStation[front];
            //不断循环，直到根据某个城市的前置编号就是起点
            if (front == start) break;
            arrayList.add(front);
        }

        //将起点放入路线数组
        if (!arrayList.contains(start)) {
            arrayList.add(start);
        }

        //将得到arrayList反转一下，即可得到最终结果
        Collections.reverse(arrayList);

        int[] res = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            res[i] = arrayList.get(i);
        }
        return res;
    }

    @Override
    public int getTotalLenByPath(int[] pathArray) {
        return getResourceConsume(pathArray, DistanceRectangle);
    }

    @Override
    public int getTotalMoneyByPath(int[] pathArray) {
        return getResourceConsume(pathArray, MoneyRectangle);
    }

    @Override
    public int getTotalTimeByPath(int[] pathArray) {
       return getResourceConsume(pathArray, TimeRectangle);
    }




    private int getResourceConsume(int[] pathArray,Integer[][] rectangle){
        int total = 0;
        int calculateNum = pathArray.length - 1;

        for (int i = 0;i < calculateNum;i++){
            total = rectangle[pathArray[i]][pathArray[i + 1]];
        }
        return total;
    }

    // 迪杰斯特拉算法
    private int[] Dijkstra(Integer start, Integer destination, final Integer[][] rectangle, int numOfStation) {
        boolean[] isVisited = new boolean[numOfStation + 1];
        int[] value = new int[numOfStation + 1];
        int[] frontStation = new int[numOfStation + 1];

        int Temp_point = start;
        Arrays.fill(value, Integer.MAX_VALUE);
        Arrays.fill(frontStation, Temp_point);
        value[Temp_point] = 0;
        isVisited[Temp_point] = true;


        for (int i = 1; i <= numOfStation - 1; i++) {
            //根据临时点刷新从这个点到各个顶点的路程长度和花费
            for (int j = 1; j <= numOfStation; j++) {
                if (rectangle[Temp_point][j] != null) {
                    if (rectangle[Temp_point][j] != 0) {
                        //如果新路径的长度小于原路径，直接全部刷新
                        if (rectangle[Temp_point][j] + value[Temp_point] < value[j]) {
                            value[j] = rectangle[Temp_point][j] + value[Temp_point];
                            frontStation[j] = Temp_point;
                        }
                    }
                }
            }
            int Min = Integer.MAX_VALUE;
            int temp = 0;
            for (int j = 1; j <= numOfStation; j++) {
                if (isVisited[j] == false && value[j] < Min) {
                    Min = value[j];
                    temp = j;
                }
            }
            Temp_point = temp;
            isVisited[Temp_point] = true;
        }

        return frontStation;

    }

    private int[] minRes(Integer start, Integer destination, final Integer[][] rectangle, Integer numOfStation) {
        //调用迪杰斯特拉算法，将计算结果存储到frontStation数组中
        int[] frontStation;
        frontStation = Dijkstra(start, destination, rectangle, numOfStation);

        return getPathByArray(frontStation, start, destination);
    }

}
