package assignment.capstone.controller;

import assignment.capstone.entity.Blog;
import assignment.capstone.repository.BlogRepository;
import assignment.capstone.dto.BlogInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auto")
@RequiredArgsConstructor
public class AutoController {
    private final BlogRepository blogRepository;

    @PostMapping("/regist")
    public void regist(@RequestBody BlogInfo blogInfo) {
        blogRepository.save(dtoToEntity(blogInfo));
    }

    private Blog dtoToEntity(BlogInfo blogInfo) {
        return new Blog(null, blogInfo.getAccessToken(), blogInfo.getPostId(), blogInfo.getBlogName());
    }
}
