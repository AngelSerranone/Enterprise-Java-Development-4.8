package com.example.Lab4_8.repository;

import com.example.Lab4_8.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // get post and author
    @Query("select p.title, a.name from Post p left join p.author a where p.id=:id")
    public List<Object[]> findPostAndAuthor(@Param("id") Long id);

}
