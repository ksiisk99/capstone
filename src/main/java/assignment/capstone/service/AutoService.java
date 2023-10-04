package assignment.capstone.service;

import assignment.capstone.dto.BlogInfo;
import assignment.capstone.dto.Comment;
import assignment.capstone.entity.Blog;
import assignment.capstone.service.gpt.ChatGptHandler;
import assignment.capstone.service.tistory.CommentReader;
import assignment.capstone.service.tistory.CommentWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoService {
    private final ChatGptHandler chatGptHandler;
    private final CommentReader commentReader;
    private final CommentWriter commentWriter;

    public void autoCommentResponse(Blog blog) {
        List<Comment> comments = commentReader.read(blog);

        removeUnnecessaryComment(comments, "https://" + blog.getBlogName());

        List<String> commentResponses = chatGptHandler.createCommentResponses(comments);

        for (int i = 0; i < comments.size(); i++) {
//            System.out.println("Comment: " + comments.get(i).getContent() + " " + comments.get(i).getParentId());
//            System.out.println("ChatGpt: " + commentResponses.get(i));
            commentWriter.write(blog, comments.get(i).getId(), commentResponses.get(i), comments.get(i).isOpen());
        }
    }

    private void removeUnnecessaryComment(List<Comment> comments, String homePage) {
        List<Comment> removeComments = new ArrayList<>();

        for (int i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            if (isParentComment(comment.getParentId())
                    //|| alreadyCompleted(comment, homePage)
            ) {
                removeComments.add(comment);
            }
        }

        removeComments.stream()
                .forEach(comment -> comments.remove(comment));
    }

    private boolean alreadyCompleted(Comment comment, String homePage) {
        if (comment.getHomepage().equals(homePage)) {
            return true;
        }
        return false;
    }

    private boolean isParentComment(String parentId) {
        return parentId.equals("");
    }


}
