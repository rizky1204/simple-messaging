package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.requesthandle.RequestHandler;
import system.service.ConversationService;
import system.vo.ResponseVO;
import system.vo.SendMessageVO;

import java.util.Date;

@RestController
@RequestMapping(path="/api/conversation")
public class ConversationController {


	@Autowired
	ConversationService conversationService;


	@RequestMapping(value = "/send-message",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseVO> updateUser(@RequestParam (value = "your-number", required = true) String number,
												 @RequestBody SendMessageVO vo) {
		RequestHandler handler = new RequestHandler() {
			@Override
			public Object processRequest() {
				return conversationService.sendMessage(vo , number);
			}
		};
		return handler.getResult();
	}

	@RequestMapping(value = "/inbox/{number}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseVO> getInboc(@PathVariable("number") String number){
		RequestHandler handler = new RequestHandler() {
			@Override
			public Object processRequest() {
				return conversationService.listMessage(number);
			}
		};
		return handler.getResult();
	}

	@RequestMapping(value = "/sent-item/{number}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseVO> getSentItem(@PathVariable("number") String number){
		RequestHandler handler = new RequestHandler() {
			@Override
			public Object processRequest() {
				return conversationService.listSentItem(number);
			}
		};
		return handler.getResult();
	}

	@GetMapping(path="/ping")
	public @ResponseBody
	Date ping() {
		Date ping = new Date();
		return ping;
	}
}
