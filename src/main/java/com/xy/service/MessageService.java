package com.xy.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.base.ResponseVo;
import com.xy.entity.dos.Message;
import com.xy.entity.vos.MessageVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Xieyong
 * @date 2019/12/16 - 12:51
 */
public interface MessageService extends IService<Message> {

    int saveMsg(MessageVo messageVo);

    List<MessageVo> toAuditMsg();

    MessageVo getMessage(Integer msgId);

    List<MessageVo> getMessageList();

    int auditMsg(Integer msgId, Integer auditResults, String auditOpinion, Integer userId);

}
