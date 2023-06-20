package assignment.capstone.tistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoService {
    private final ChatGptHandler chatGptHandler;
    private final CommentReader commentReader;
    private final CommentWriter commentWriter;

    public void autoCommentResponse(BlogInfo blogInfo) {
        List<Comment> comments = commentReader.read(blogInfo);

        List<String> commentResponses = chatGptHandler.generateText(comments);

        for (int i = 0; i < comments.size(); i++) {
            System.out.println("Comment: " + comments.get(i).getContent() + " " + comments.get(i).getParentId());
            System.out.println("ChatGpt: " + commentResponses.get(i));
            if (isParentComment(comments.get(i).getParentId())) {
                commentWriter.write(blogInfo, comments.get(i).getId(), commentResponses.get(i), comments.get(i).isOpen());
            }
        }
    }

    private boolean isParentComment(String parentId) {
        return parentId.equals("");
    }

}
