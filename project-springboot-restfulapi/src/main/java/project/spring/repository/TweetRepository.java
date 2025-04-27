package project.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.spring.entities.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {}
