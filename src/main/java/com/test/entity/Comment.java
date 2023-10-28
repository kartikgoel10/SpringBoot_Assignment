package com.test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long commentId;

	@NotBlank(message = "Message cannot be blank")
	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "comment_date_time", nullable = false)
	private LocalDateTime commentDateTime;

	@ManyToOne
	@JoinColumn(name = "posted_by_user_id", nullable = false)
	private User postedByUser;

}