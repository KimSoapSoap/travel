package green.mtcoding.travel.global.error;

import green.mtcoding.travel.global.error.ex.*;

import green.mtcoding.travel.global.util.Resp;
import green.mtcoding.travel.global.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//일반적으로 뷰를 요청받았을 때 쓰는 핸들러와 Api요청(Ajax요청) 받았을 때 쓰는 핸들러 이렇게 2개 만들어 준다.
@RestControllerAdvice
public class GlobalApiExceptionHandler {

    // 유효성 검사 실패 (잘못된 클라이언트의 요청)
    @ExceptionHandler(ExceptionApi400.class)
    public ResponseEntity<?> ex400(Exception e) {
        //왜 상태코드가 2개 보내느냐면 Resp의 body데이터에서 프론트 개발자가 편하게 확인 가능. 없으면 까봐야됨. 즉 배려이다.
        //HttpStatus로 전달하는 상태코드가 실제 전달 상태코드이다.
        return new ResponseEntity<>(Resp.fail(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // 인증 실패 (클라이언트가 인증없이 요청했거나, 인증을 하거나 실패했거나)
    @ExceptionHandler(ExceptionApi401.class)
    public ResponseEntity<?> ex401(Exception e) {
        return new ResponseEntity<>(Resp.fail(401, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    // 권한 실패 (인증은 되어 있는데, 삭제하려는 게시글이 내가 적은게 아니다)
    @ExceptionHandler(ExceptionApi403.class)
    public ResponseEntity<?> ex403(Exception e) {
        return new ResponseEntity<>(Resp.fail(403, e.getMessage()), HttpStatus.FORBIDDEN);
    }

    // 서버에서 리소스(자원) 찾을 수 없을때
    @ExceptionHandler(ExceptionApi404.class)
    public ResponseEntity<?> ex404(Exception e) {
        return new ResponseEntity<>(Resp.fail(404, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    // 서버에서 심각한 오류가 발생했을때 (알고 있을 때)
    @ExceptionHandler(ExceptionApi500.class)
    public ResponseEntity<?> ex500(Exception e) {
        return new ResponseEntity<>(Resp.fail(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //그냥 핸들러 혹은 Api 핸들러 둘 중 한 곳에만 있으면 된다(충돌 난다)
    // 서버에서 심각한 오류가 발생했을때 (모를 때)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ex(Exception e) {
        return new ResponseEntity<>(Resp.fail(500, "알 수 없는 에러입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}







