package com.test.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Comment;
import com.test.entity.User;
import com.test.repository.CommentRepository;
import com.test.service.CommentService;
import com.test.service.UserService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public String addComment(String commentFrom, String commentTo, String message) {
        // Validations for user names and message
        if (!isValidUserName(commentFrom) || !isValidUserName(commentTo) || message.isEmpty()) {
            throw new IllegalArgumentException("Invalid Request - Invalid user names or empty message");
        }

        User fromUser = userService.addUser(commentFrom, commentTo);
        User toUser = userService.addUser(commentTo, commentFrom);

        Comment newComment = new Comment();
        newComment.setMessage(message);
        newComment.setCommentDateTime(LocalDateTime.now());
        newComment.setPostedByUser(fromUser);

        commentRepository.save(newComment);

        return "Comment added successfully";
    }

    @Override
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByPostedByUserUserId(userId);
    }

    private boolean isValidUserName(String userName) {
        return !userName.isEmpty() && userName.matches("^[a-zA-Z]+$");
    }
}