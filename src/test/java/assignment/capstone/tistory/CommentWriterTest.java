package assignment.capstone.tistory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentWriterTest {
    @Test
    void writeComment() {
        CommentWriter commentWriter = new CommentWriter();
        BlogInfo blogInfo = new BlogInfo("accessToken", 4, "blogName");

        String parentId = "15766892";
        String content = "world";
        boolean open = true;

        assertEquals(true, commentWriter.write(blogInfo, parentId, content, open));
    }
}