package com.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xy.entity.dos.Audit;
import com.xy.entity.dos.Message;
import com.xy.entity.vos.MessageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Xieyong
 * @date 2019/12/16 - 12:54
 */
public interface MessageMapper extends BaseMapper<Message>{

    //根据消息id获取待审核信息
    List<MessageVo> toAuditMsg();

    Audit selectAuditByMsgId(@Param("msgId") Integer msgId);
}
