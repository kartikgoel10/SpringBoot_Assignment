package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@NotBlank(message = "Comment from cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Comment from should only contain alphabets")
	@Column(name = "comment_from", nullable = false)
	private String commentFrom;

	@NotBlank(message = "Comment to cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Comment to should only contain alphabets")
	@Column(name = "comment_to", nullable = false)
	private String commentTo;
}
