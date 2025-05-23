package com.example.demo.exception;
import com.example.demo.dto.response.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    ResponseEntity<APIResponse> handlingArgumentNotValid(MethodArgumentNotValidException exception) {
        APIResponse apiResponse = new APIResponse();
        ErrorCode errorCode = ErrorCode.KEY_INVALID;

        try {
            errorCode = ErrorCode.valueOf(exception.getFieldError().getDefaultMessage());
        } catch (IllegalArgumentException var5) {
        }

        apiResponse.setCode(errorCode.code);
        apiResponse.setMessage(errorCode.message);
        return ResponseEntity.badRequest().body(apiResponse);
    }
    //Xử lý ApplicationException
    @ExceptionHandler({ApplicationException.class})
    ResponseEntity<APIResponse> handlingApplicationException(ApplicationException e) {
        APIResponse apiResponse = new APIResponse();
        ErrorCode errorCode = e.getErrorCode();
        apiResponse.setCode(errorCode.code);
        apiResponse.setMessage(errorCode.message);
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
    }

    @ExceptionHandler({AccessDeniedException.class})
    ResponseEntity<APIResponse> handlingAccessDeniedException(AccessDeniedException e){
        ErrorCode errorCode = ErrorCode.ACCESSDENIED;
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                APIResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage()).build()
        );
    }
}
