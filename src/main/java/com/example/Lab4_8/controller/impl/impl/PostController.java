package com.example.Lab4_8.controller.impl.impl;
import com.example.Lab4_8.controller.impl.interfaces.IPostController;
import com.example.Lab4_8.model.Post;
import com.example.Lab4_8.service.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController implements IPostController {


    @Autowired
    private IPostService postService;

    @GetMapping("/post-and-author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findPostAndAuthor(@PathVariable Long id) {
        return postService.findPostAndAuthor(id);
    }

    @PostMapping("/new-post")
    @ResponseStatus(HttpStatus.CREATED)
    public Post addNewPost(@RequestBody @Valid Post post) {
        return postService.addNewPost(post);
    }

    @PutMapping("update-info/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePostInformation(@PathVariable Long id, @RequestBody @Valid Post post) {
        postService.updatePostInformation(id, post);
    }

    @DeleteMapping("/delete-post/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
