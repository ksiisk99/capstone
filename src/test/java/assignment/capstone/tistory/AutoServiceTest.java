package assignment.capstone.tistory;

import assignment.capstone.dto.BlogInfo;
import assignment.capstone.service.AutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AutoServiceTest {
    @Autowired
    private AutoService autoService;

    @Test
    void autoComment() {
        BlogInfo blogInfo = new BlogInfo("accessToken", 4, "blogName");

        assertDoesNotThrow(() -> autoService.autoCommentResponse(blogInfo));
    }
}