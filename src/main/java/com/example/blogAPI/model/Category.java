package com.example.blogAPI.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.blogAPI.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "categogies")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category extends UserDateAudit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "categogy",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Post> posts;

	public Category(String name) {
		this.name = name;
	}
	
	public List<Post> getPosts(){
		return posts == null ? null : new ArrayList<>(this.posts);
	}
	
	public void setPosts(List<Post> posts) {
		if(posts == null) {
			this.posts = null;
		}else {
			this.posts = Collections.unmodifiableList(posts);
		}
	}
}
