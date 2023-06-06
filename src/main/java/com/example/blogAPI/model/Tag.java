package com.example.blogAPI.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.blogAPI.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tags")
public class Tag extends UserDateAudit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "post_tag",joinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "post_id",referencedColumnName = "id"))
	private List<Post> posts;
	
	public Tag(String name) {
		this.name = name;
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
}
