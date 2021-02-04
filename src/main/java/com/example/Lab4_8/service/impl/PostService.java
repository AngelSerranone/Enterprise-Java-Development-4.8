package com.example.Lab4_8.service.impl;

import com.example.Lab4_8.model.Author;
import com.example.Lab4_8.model.Post;
import com.example.Lab4_8.repository.PostRepository;
import com.example.Lab4_8.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void saveAll(List<Post> postList) {
        postRepository.saveAll(postList);
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }

    // get post and author
    public List<Object[]> findPostAndAuthor(Long id) {
        return postRepository.findPostAndAuthor(id);
    }

    // add post
    public Post addNewPost(Post post) {
        return postRepository.save(post);
    }

    // update post
    public void updatePostInformation(Long id, Post post) {
        if (postRepository.findById(id).isPresent()) {
            post.setId(id);
            postRepository.save(post);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
    }

    // delete post
    public void deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()){
            postRepository.delete(post.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not course");
        }
    }


}
