package com.xy.entity.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 物资借用申请表
 * @author Xieyong
 * @date 2019/12/15 - 14:03
 */
@TableName("tbl_borrow")
@Data
public class Borrow implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id; //物资借用申请表id
    private Integer borinfoId; //申请借用信息表id
    private Integer typeId;   //物资类别id
    private Integer borrowNumber;  //借用数量
    private Integer borrowOpinion; //是否同意出借：null:待审核 1.同意，0.不同意
    private Integer type;   //借用类型：1.预借(还未借出)，2.已借（待还）3.已还（作废）
    private String borrowAddress;  //出借物资地点
    private String returnAddress;  //归还物资地点(可以默认为出借物资地点)
    private Integer del_flag; //删除标记：1：正常，0：注销',

}
