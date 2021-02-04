package com.example.Lab4_8.service.interfaces;


import com.example.Lab4_8.model.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPostService {

    // get post and author
    public List<Object[]> findPostAndAuthor(Long id);

    // add post
    public Post addNewPost(Post post);

    // update post
    public void updatePostInformation(Long id, Post post);

    // delete post
    public void deletePost(Long id);
}
