package assignment.capstone.service;

import assignment.capstone.dto.Comment;
import assignment.capstone.entity.Blog;
import assignment.capstone.service.gpt.ChatGptHandler;
import assignment.capstone.service.tistory.CommentReader;
import assignment.capstone.service.tistory.CommentWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
            commentWriter.write(blog, comments.get(i).getId(), commentResponses.get(i), comments.get(i).isOpen());
        }
    }

    private void removeUnnecessaryComment(List<Comment> comments, String homePage) {
        List<Comment> removeComments = new ArrayList<>();

        for (int i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            if (!isParentComment(comment.getParentId())
                    || isOwnComment(comment, homePage)
            ) {
                removeComments.add(comment);
            }
        }
        removeComments.stream()
                .forEach(comment -> comments.remove(comment));

        Set<String> alreadyCommentIds = getAlreadyCommentIds(comments, homePage);
        Iterator<Comment> iterator = comments.iterator();
        while (iterator.hasNext()) { //이미 답글한 댓글은 제거
            Comment comment = iterator.next();
            if (alreadyCommentIds.contains(comment.getId())) {
                iterator.remove();
            }
        }

    }

    private Set<String> getAlreadyCommentIds(List<Comment> comments, String homePage) {
        return comments.stream()
                .filter(c -> !isParentComment(c.getParentId()) && isAlreadyResponse(c.getHomepage(), homePage))
                .map(c -> c.getParentId())
                .collect(Collectors.toCollection(HashSet::new));
    }

    private boolean isAlreadyResponse(String s, String s2) {
        if (s.equals(s2)) {
            return true;
        }
        return false;
    }

    private boolean isOwnComment(Comment comment, String homePage) {
        if (comment.getHomepage().equals(homePage)) {
            return true;
        }
        return false;
    }

    private boolean isParentComment(String parentId) {
        return parentId.equals("");
    }


}
