package com.seal.comman.vo;

import com.alibaba.ans.shaded.com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/11/22 18:01
 * @description
 **/
@Data
public class LotteryNumberVO {

    /**
     * uid
     */
    private String uid;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 领取时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date receiveTime;

    /**
     * 抽奖次数
     */
    private int number;

//{
//    "memberId": "50cff40d-1929-4d35-9a24-d9b45a18e83f",
//        "number": 120,
//        "receiveTime": "2019-11-30",
//        "uid": "50cff40d-1929-4d35-9a24-d9b45a18e83f"
//}


    public static LotteryNumberVO createUserLotteryNumber(int defaultNumber,String uid) {
        LotteryNumberVO lotteryNumber = new LotteryNumberVO();
        lotteryNumber.setUid(uid);
        lotteryNumber.setMemberId(uid);
        lotteryNumber.setReceiveTime(new Date());
        lotteryNumber.setNumber(defaultNumber);
        return lotteryNumber;
    }
}
