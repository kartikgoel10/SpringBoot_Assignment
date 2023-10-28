package com.test.service;

import java.util.List;

import com.test.entity.Comment;

public interface CommentService {
	
	String addComment(String commentFrom, String commentTo, String message);

    List<Comment> getCommentsByUserId(Long userId);

}
