package com.example.blogAPI.model.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.example.blogAPI.model.Album;
import com.example.blogAPI.model.Comment;
import com.example.blogAPI.model.Post;
import com.example.blogAPI.model.Todo;
import com.example.blogAPI.model.audit.DateAudit;
import com.example.blogAPI.model.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }) })
public class User extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "first_name")
	@Size(max = 40)
	private String firstName;

	@NotBlank
	@Column(name = "last_name")
	@Size(max = 40)
	private String lastName;

	@NotBlank
	@Column(name = "username")
	@Size(max = 15)
	private String username;

	@NotBlank
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(max = 100)
	@Column(name = "password")
	private String password;

	@NotBlank
	@NaturalId
	@Size(max = 40)
	@Email
	@Column(name = "email")
	private String email;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;

	private String phone;

	private String website;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Todo> todos;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Album> albums;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> posts;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "company_id")
	private Company company;

	public User(String firstName, String lastName, String username, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public List<Todo> getTodos(){
		return todos == null ? null : new ArrayList<>(todos);
	}
	
	public void setTodos(List<Todo> todos) {
		if(todos == null) {
			this.todos = null;
		}else {
			this.todos = Collections.unmodifiableList(todos);
		}
	}
	
	public List<Album> getAlbums(){
		return albums == null ? null : new ArrayList<>(albums);
	}
	
	public void setAlbums(List<Album> albums) {
		if(albums == null) {
			this.albums = null;
		}else {
			this.albums = Collections.unmodifiableList(albums);
		}
	}
	
	public List<Post> getPosts(){
		return posts == null ? null : new ArrayList<>(posts);
	}
	
	public void setPosts(List<Post> posts) {
		if(posts == null) {
			this.posts = null;
		}else {
			this.posts = Collections.unmodifiableList(posts);
		}
	}
	
	public List<Role> getroles(){
		return roles == null ? null : new ArrayList<>(roles);
	}
	
	public void setRoles(List<Role> roles) {
		if(roles == null) {
			this.roles = null;
		}else {
			this.roles = Collections.unmodifiableList(roles);
		}
	}
	
	public List<Comment> getComments(){
		return comments == null ? null : new ArrayList<>(comments);
	}
	
	public void setComments(List<Comment> comments) {
		if(comments == null) {
			this.comments = null;
		}else {
			this.comments = Collections.unmodifiableList(comments);
		}
	}
}
