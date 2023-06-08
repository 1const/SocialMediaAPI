package com.akmal.socialmediaapi.repository;

import com.akmal.socialmediaapi.domain.Post;
import com.akmal.socialmediaapi.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findPostsByAuthor(User author, Pageable pageable);

    Page<Post> findPostsByAuthorIn(List<User> users, Pageable pageable);
}
