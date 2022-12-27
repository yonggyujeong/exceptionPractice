package com.example.exceptionPractice.api;

import com.example.exceptionPractice.dto.MemberDto;
import com.example.exceptionPractice.exHandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiV2ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illeglExceptionHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());  // RestController 이기 때문에 json으로 반환
    }

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            log.info(id);
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        return new MemberDto(id, "홍길동");
    }
}
