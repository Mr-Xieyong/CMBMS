package com.xy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.base.ResponseVo;
import com.xy.constant.BaseConstant;
import com.xy.entity.dos.Audit;
import com.xy.entity.dos.Message;
import com.xy.entity.vos.MessageVo;
import com.xy.enums.ErrorEnum;
import com.xy.exception.BusinessException;
import com.xy.mapper.AuditMapper;
import com.xy.mapper.MessageMapper;
import com.xy.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xieyong
 * @date 2019/12/16 - 12:51
 */
@Service
public class MessageServiceImp extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private AuditMapper auditMapper;

    public int saveMsg(MessageVo messageVo) {

        if (StringUtils.isEmpty(messageVo.getTitle())) {
            throw new BusinessException(ErrorEnum.TITLE_NOT_NULL);
        }
        if (StringUtils.isEmpty(messageVo.getContent())) {
            throw new BusinessException(ErrorEnum.CONTENT_NOT_NULL);
        }

        //插入消息表
        Message message = new Message();
        message.setMsgType(messageVo.getMsgType());
        message.setTitle(messageVo.getTitle());
        message.setContent(messageVo.getContent());
        message.setPic(messageVo.getPic());
        message.setCreateBy(messageVo.getUserId());
        message.setCreateTime(new Date());
        message.setPublish(BaseConstant.ZERO);
        message.setDel_flag(BaseConstant.ONE);
        messageMapper.insert(message);

        Integer msgId = message.getId();

        //插入审核表
        Audit audit = new Audit();
        audit.setMsgId(msgId);
        audit.setCreateBy(messageVo.getUserId());
        audit.setCreateTime(new Date());
        audit.setDelFlag(BaseConstant.ONE);
        auditMapper.insert(audit);

        return msgId;

    }

    public List<MessageVo> toAuditMsg() {
        List<MessageVo> msg = messageMapper.toAuditMsg();
        return msg;
    }

    public int auditMsg(Integer msgId, Integer auditResults, String auditOpinion, Integer userId) {
        Audit audit = messageMapper.selectAuditByMsgId(msgId);
        if (!StringUtils.isEmpty(audit.getAuditResults())) {
            throw new BusinessException(ErrorEnum.AUDIT_IS_OK);
        }
        auditMapper.updateAuditMsg(msgId, auditResults, auditOpinion, userId);
        return 1;
    }


}
