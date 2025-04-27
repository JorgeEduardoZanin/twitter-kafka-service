package project.spring.resources;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.entities.Tweet;
import project.spring.services.TweetServices;

@RestController
@RequestMapping(value = "/tweet")
public class TweetResources {

	@Autowired
	private TweetServices services;
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO GET 
	 *
	 *
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	@Cacheable("listAll")
	public ResponseEntity<List<Tweet>> listAll(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int pageSize) throws InterruptedException{
		
		List<Tweet> tweets = services.listAll(page, pageSize);
		Thread.sleep(3000);
        return ResponseEntity.ok(tweets);
	}
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO POST
	 *
	 *
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('SCOPE_BASIC')")
	public ResponseEntity<Tweet> createTweet(@RequestBody Tweet tweet, JwtAuthenticationToken token){
		return ResponseEntity.status(HttpStatus.CREATED).body(services.createTweet(tweet, token));
	}
	/*
	 *
	 * 
	 CONTROLADORES DO TIPO DELETE
	 *
	 *
	 */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('SCOPE_BASIC') or hasAuthority('SCOPE_ADMIN')")
	public ResponseEntity<String> deleteTweet(@PathVariable Long id, JwtAuthenticationToken token){
		return ResponseEntity.ok(services.deleteTweet(id, token));
	}
	
	
}
