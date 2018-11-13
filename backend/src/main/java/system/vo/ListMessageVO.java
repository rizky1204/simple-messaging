package system.vo;

import lombok.Data;

import java.util.List;

@Data
public class ListMessageVO {
     private List<MessageVO> messageVOList;
     private List<SentItemMessageVO> sentItemMessageVOS;
}
