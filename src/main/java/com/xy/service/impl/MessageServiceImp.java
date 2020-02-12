package com.xy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.entity.dos.Message;
import com.xy.mapper.MessageMapper;
import com.xy.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * @author Xieyong
 * @date 2019/12/16 - 12:51
 */
@Service
public class MessageServiceImp extends ServiceImpl<MessageMapper, Message> implements MessageService{
}
