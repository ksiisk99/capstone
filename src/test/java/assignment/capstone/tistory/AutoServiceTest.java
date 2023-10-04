package assignment.capstone.tistory;

import assignment.capstone.entity.Blog;
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
        Blog blog = new Blog(1L,"accessToken", 4, "blogName");

        assertDoesNotThrow(() -> autoService.autoCommentResponse(blog));
    }
}