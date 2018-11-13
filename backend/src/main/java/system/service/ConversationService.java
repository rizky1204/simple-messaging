package system.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import system.domain.Conversation;
import system.exception.SimpleMessagingException;
import system.repository.ConversationRepository;
import system.vo.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@SuppressWarnings("unchecked")
public class ConversationService {


    @Autowired
    ConversationRepository conversationRepository;

    @Transactional
    public Boolean sendMessage(SendMessageVO vo , String number){
        String methodName ="sendMessage";
        log.info(methodName);
        String errorMsg =  validateMessage(vo);
        log.info(errorMsg);
        if(!errorMsg.isEmpty()) throw new SimpleMessagingException(errorMsg);
        Conversation model = new Conversation();
        model.setReciever(vo.getDestinationNumber());
        model.setMessage(vo.getMessage());
        model.setSender(number);
        model.setCreatedBy(number);
        conversationRepository.save(model);
        log.info("Message has been delivered");

        return Boolean.TRUE;
    }

    public List<MessageVO> listMessage(String number){
        String methodName= "listMessage";
        log.info(methodName);
        List<Conversation> conversations = conversationRepository.findByReciever(number);
        if(conversations.isEmpty()) throw new SimpleMessagingException("inbox not found");
        ListMessageVO messageVOList = new ListMessageVO();
        List<MessageVO> messageVOS = new ArrayList<>();
        for(Conversation conversation : conversations){
            MessageVO vo = new MessageVO();
            vo.setFrom(conversation.getSender());
            vo.setMessage(conversation.getMessage());
            messageVOS.add(vo);
        }

        messageVOList.setMessageVOList(messageVOS);
        return messageVOS;

    }

    public List<SentItemMessageVO> listSentItem(String number){
        String methodName= "listMessage";
        log.info(methodName);
        List<Conversation> conversations = conversationRepository.findBySender(number);
        if(conversations.isEmpty()) throw new SimpleMessagingException("sent item not found");
        ListMessageVO messageVOList = new ListMessageVO();
        List<SentItemMessageVO> messageVOS = new ArrayList<>();
        for(Conversation conversation : conversations){
            SentItemMessageVO vo = new SentItemMessageVO();
            vo.setTo(conversation.getReciever());
            vo.setMessage(conversation.getMessage());
            messageVOS.add(vo);
        }

        messageVOList.setSentItemMessageVOS(messageVOS);
        return messageVOS;

    }

    public String validateMessage(SendMessageVO vo){
        String errorMessage = "";
        if(StringUtils.isEmpty(vo.getMessage())){
            errorMessage = "Message can not be empty";
        }

        if(StringUtils.isEmpty(vo.getDestinationNumber())){
            errorMessage = "Destination Number can not be empty";
        }
        return errorMessage;
    }


}
