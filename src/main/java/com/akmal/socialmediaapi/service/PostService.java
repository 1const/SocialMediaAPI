package com.akmal.socialmediaapi.service;

import com.akmal.socialmediaapi.domain.Subscription;
import com.akmal.socialmediaapi.dto.PostDTO;
import com.akmal.socialmediaapi.domain.Post;
import com.akmal.socialmediaapi.domain.User;
import com.akmal.socialmediaapi.exception.BadRequestException;
import com.akmal.socialmediaapi.exception.AccessDeniedException;
import com.akmal.socialmediaapi.repository.PostRepository;
import com.akmal.socialmediaapi.repository.SubscriptionRepository;
import com.akmal.socialmediaapi.repository.UserRepository;
import com.akmal.socialmediaapi.security.UserPrincipal;
import com.akmal.socialmediaapi.payload.ActivityFeedResponse;
import com.akmal.socialmediaapi.payload.PostsByAuthorIdResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    public static final String CREATED_AT = "createdAt";

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final ModelMapper modelMapper;


    public PostDTO findPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        postDTO.setAuthorId(id);
        return postDTO;
    }

    @Transactional
    public PostDTO savePost(PostDTO postDTO, UserPrincipal currentUser) {
        Long id = currentUser.getUser().getId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setCreatedAt(new Date());

        post.setAuthor(user);
        user.getPosts().add(post);

        postRepository.save(post);

        postDTO.setAuthorId(currentUser.getUser().getId());
        return postDTO;
    }

    @Transactional
    public PostDTO updatePost(Long postId, PostDTO postDTO, UserPrincipal currentUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Long authorId = post.getAuthor().getId();
        Long requesterId = currentUser.getUser().getId();

        if (!requesterId.equals(authorId)) {
            throw new AccessDeniedException("Don't have permission");
        }

        postRepository.save(modelMapper.map(postDTO, Post.class));

        postDTO.setAuthorId(currentUser.getUser().getId());
        return postDTO;
    }

    @Transactional
    public void deletePost(Long postId, UserPrincipal currentUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Long authorId = post.getAuthor().getId();
        Long requesterId = currentUser.getUser().getId();

        if (!requesterId.equals(authorId)) {
            throw new AccessDeniedException("Don't have permission");
        }

        postRepository.deleteById(postId);
    }

    public ActivityFeedResponse getActivityFeed(UserPrincipal userPrincipal, Integer page, Integer size) {
        validatePageNumberAndSize(page, size);

        User user = userRepository
                .findById(userPrincipal.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
        List<Subscription> subscriptions = subscriptionRepository.findBySubscriber(user);
        List<User> users = subscriptions.stream()
                .map(Subscription::getSubscriber)
                .toList();

        Page<Post> postsPage = postRepository.findPostsByAuthorIn(users, pageable);

        List<PostDTO> activityFeed = postsPage.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .toList();

        return new ActivityFeedResponse(activityFeed, page, postsPage.getTotalPages());
    }

    public PostsByAuthorIdResponse findPostsByAuthorId(Long authorId, int page, int size) {
        validatePageNumberAndSize(page, size);

        User user = userRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Post> pages = postRepository.findPostsByAuthor(user, pageable);

        List<Post> posts = pages.getNumberOfElements() == 0 ? Collections.emptyList() : pages.getContent();
        List<PostDTO> list = posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).toList();

        return new PostsByAuthorIdResponse(list);
    }

    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero");
        }

        if (size < 0) {
            throw new BadRequestException("Size number cannot be less than zero");
        }
    }


}
