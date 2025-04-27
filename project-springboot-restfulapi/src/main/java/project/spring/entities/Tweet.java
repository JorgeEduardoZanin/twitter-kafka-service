package project.spring.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tweet")
public class Tweet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "tweet_id")
	public Long tweetId;
	
	@ManyToOne
	@JsonBackReference
	private Usuario usuario;
	
	private String content;
	
	@CreationTimestamp
	private Instant createTimestamp;
	
	public Tweet() {
		
	}

	public Tweet(Long tweetId, Usuario usuario, String content, Instant createTimestamp) {
		this.tweetId = tweetId;
		this.usuario = usuario;
		this.content = content;
		this.createTimestamp = createTimestamp;
	}

	public Long getTweetId() {
		return tweetId;
	}

	public void setTweetId(Long tweetId) {
		this.tweetId = tweetId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Instant createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	
	

}
