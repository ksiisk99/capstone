package assignment.capstone.tistory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auto")
@RequiredArgsConstructor
public class AutoController {
    private final AutoService autoService;

    @PostMapping("/regist")
    public void regist(@RequestBody BlogInfo blogInfo) {
        autoService.autoCommentResponse(blogInfo);
    }
}
