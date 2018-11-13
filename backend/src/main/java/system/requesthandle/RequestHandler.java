package system.requesthandle;

import org.springframework.http.ResponseEntity;
import system.exception.SimpleMessagingException;
import system.vo.ResponseVO;

public abstract class RequestHandler {

    public ResponseEntity<ResponseVO> getResult() {
        ResponseVO result = new ResponseVO();
        try {
            Object obj = processRequest();

            if (obj instanceof ResponseVO) {
                result = (ResponseVO) obj;
                return RestUtil.getJsonResponse(result);
            }

            if (obj != null) {
                result.setResponse(StatusCode.OK.name());
                result.setResult(obj);
            } else {
                result.setResponse(StatusCode.OK.name());
                result.setResult(null);
            }
        } catch (SimpleMessagingException e) {
            result.setResponse(e.getCode().name());
            result.setResult(e.getMessage());
        }
        return RestUtil.getJsonResponse(result);
    }

    public abstract Object processRequest();
}
