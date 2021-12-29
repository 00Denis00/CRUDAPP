package com.dsidorov.crudapp.controller;
import com.dsidorov.crudapp.model.Post;
import com.dsidorov.crudapp.repository.PostRepository;

import java.util.List;

public class PostController
{
    PostRepository postRepository = new PostRepository(){};
    public String getById(Integer id)
    {
        String result = postRepository.getById(id);
        return result;
    }
    public void deleteById(Integer id)
    {
        postRepository.deleteById(id);
    }
    public List<Post> getAll()
    {
        List<Post> posts = postRepository.getAll();
        return posts;
    }
    public void save(Post post)
    {
        postRepository.save(post);
    }
    public void update(Post post)
    {
        postRepository.update(post);
    }
}
