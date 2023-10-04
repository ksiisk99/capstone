package assignment.capstone.tistory;

import assignment.capstone.entity.Blog;
import assignment.capstone.service.tistory.CommentWriter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentWriterTest {
    @Test
    void writeComment() {
        CommentWriter commentWriter = new CommentWriter();
        Blog blog = new Blog(1L, "accessToken", 4, "blogName");

        String parentId = "15766892";
        String content = "world";
        boolean open = true;

        assertEquals(true, commentWriter.write(blog, parentId, content, open));
    }
}