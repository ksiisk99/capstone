package assignment.capstone.schedule;

import assignment.capstone.entity.Blog;
import assignment.capstone.repository.BlogRepository;
import assignment.capstone.service.AutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentScheduler {
    private final AutoService autoService;
    private final BlogRepository blogRepository;

    @Scheduled(fixedDelay = 10000L)
    private void schedule() {
        log.info("schedule start");
        List<Blog> blogs = blogRepository.findAll();

        writeComment(blogs);

        //blogRepository.deleteAll(blogs);
    }

    private void writeComment(List<Blog> blogs) {
        try {
            blogs.stream()
                    .forEach(blog -> autoService.autoCommentResponse(blog));
        } catch (Exception e) {
            log.error("error: {}", e);
        }
    }
}
