package com.example.blogAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.blogAPI.model.audit.UserDateAudit;
import com.example.blogAPI.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends UserDateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	@NotBlank
	@Size(min = 4, max = 50)
	private String name;

	@Column(name = "email")
	@NotBlank
	@Size(min = 4, max = 50)
	@Email
	private String email;

	@Column(name = "body")
	@NotBlank
	@Size(min = 10, message = "Comment body must be minimum 10 characters")
	private String body;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Comment(@NotBlank @Size(min = 10, message = "Comment body must be minimum 10 characters") String body) {
		this.body = body;
	}

	@JsonIgnore
	public Post getPost() {
		return post;
	}
	
	@JsonIgnore
	public User getUser() {
		return user;
	}
}
