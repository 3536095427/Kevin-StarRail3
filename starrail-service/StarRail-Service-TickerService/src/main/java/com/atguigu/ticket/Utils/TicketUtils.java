package com.atguigu.ticket.Utils;


import cn.hutool.core.util.RandomUtil;
import com.atguigu.model.pojo.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketUtils {

    private static final Random random = new Random();

    private static final String[] train_numbers = {
            "K123", "K456", "K789", "K1011", "K1314", "K1617", "K1920", "K2122", "K2425", "K2728",
            "Z123", "Z456", "Z789", "Z1011", "Z1314", "Z1617", "Z1920", "Z2122", "Z2425", "Z2728",
            "T123", "T456", "T789", "T1011", "T1314", "T1617", "T1920", "T2122", "T2425", "T2728"
    };

    // 获取需要的随机车票
    public List<Ticket> getRandomTicketsByFactor(List<Ticket> factors, int generateNum){
        // 获取每个因子需要生成的车票数量,即膨胀因子
        int factorNum = factors.size();
        int expansionFactor = generateNum / factorNum;


        List<Ticket> tickets = new ArrayList<>(generateNum);

        for(Ticket factor : factors ){
            for(int i = 0;i < expansionFactor;i++){
                tickets.add(generateTicketByFactor(factor));
            }
        }

        // 如果数量不够，继续生成
        int leftNum = generateNum % factorNum;
        if(leftNum != 0){
            // 使用第一个因子生成缺少的车票;
            Ticket firstFactor = factors.get(0);
            for(int i = 0;i < leftNum;i++){
                tickets.add(generateTicketByFactor(firstFactor));
            }
        }

        return tickets;
    }


    private Ticket generateTicketByFactor(Ticket factor){
        // 生成随机id;
        String randomTicketId = generateRandomTicketId();

        // 生成随机出发时间
        LocalDateTime randomStartTime = generateRandomStartTime(factor.getStartTime().toLocalDate());

        // 生成随机消耗分钟数量
        LocalTime randomSpendTime = generateRandomSpendMinutes(factor.getTime());

        int minutes = randomSpendTime.getHour() * 60 + randomSpendTime.getMinute();
        // 根据随机分钟数得到抵达时间
        LocalDateTime arrivalTime = randomStartTime.plusMinutes(minutes);

        // 生成随机价格
        int randomPrice = generateRandomPrice(factor.getPrice());

        // 生成随机距离
        int randomDistance = generateRandomDistance(factor.getDistance());

        // 生成随机车次
        String train = randomTrain();

        // 生成随机座位号码
        String seat = randomSeat();

        return new Ticket(randomTicketId,
                factor.getStartStation(),
                factor.getDestinationStation(),
                randomStartTime,
                arrivalTime,
                train,
                seat,
                randomDistance,
                randomSpendTime,
                randomPrice,
                factor.getPathInfo());
    }




    // 随机生成车票id
    private static String generateRandomTicketId() {
        return RandomUtil.randomNumbers(8);
    }


    // 随机生成出发时间
    private static LocalDateTime generateRandomStartTime(LocalDate startDate){
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        LocalTime time = LocalTime.of(hour, minute);
        return LocalDateTime.of(startDate,time);
    }

    // 随机生成消耗时间
    private static LocalTime generateRandomSpendMinutes(LocalTime factor){
        // 波动幅度（-30分钟 -- 30分钟）
        return factor.plusMinutes(RandomUtil.randomInt(-20,20));
    }

    // 随机生成价格
    private static int generateRandomPrice(int factor){
        // 波动幅度（-20块 -- 20块）
        return factor + RandomUtil.randomInt(-10,11);
    }

    // 随机生成距离
    private static int generateRandomDistance(int factor){
        // 波动幅度（-100km -- 100km）
        return factor + RandomUtil.randomInt(-30, 30);
    }

    // 生成随机车次
    private static String randomTrain(){
        return RandomUtil.randomEle(train_numbers);
    }

    // 生成随机座位号码
    private static String randomSeat(){
        String carriage = String.format("%d%d车", RandomUtil.randomInt(2),RandomUtil.randomInt(1,10));
        String seat = String.format("%d座",RandomUtil.randomInt(1, 71));
        return carriage + seat;
    }
}
