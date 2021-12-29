package com.dsidorov.crudapp.repository;

import com.dsidorov.crudapp.model.Post;
import com.dsidorov.crudapp.model.Tag;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public interface PostRepository
{
    Gson gson = new Gson();
    public default List<Post> getAll() {
        //Выводит все элементы файла
        List<Post> posts = getAllPostsInternal();
        return posts;
    }

    public default String getById(Integer id) {
        //Показывает элемент по ID
        List<Post> posts = getAllPostsInternal();
        int z = 0;
        String fname = "";
        String lname = "";
        String tg = "";
        for(int i = 0; i < posts.size(); i++)
        {
            Post T = posts.get(i);
            int j = T.getId();
            if(j == id)
            {
                fname = T.getFirstName();
                lname = T.getLastName();
                tg = T.getTags();
            }
        }
        String result = "First name: " + fname + "\nLast name: " + lname + "\nTags: " + tg;
        return result;
    }

    public default void save(Post post) {
        //Создает элемент
        List<Post> posts = getAllPostsInternal();
        posts.add(post);
        writeToFile(posts);
    }

    public default void update(Post post) {
        //Изменяет элемент
        List<Post> posts = getAllPostsInternal();
        int id = post.getId();
        int z = 0;
        for(int i = 0; i < posts.size(); i++)
        {
            Post T = posts.get(i);
            int j = T.getId();
            if(j == id)
            {
                posts.remove(T);
                posts.add(post);
            }
        }
        writeToFile(posts);
    }

    public default void deleteById(Integer id) {
        //Удаляет элемент по ID
        int z = 0;
        List<Post> posts = getAllPostsInternal();
        for(int i = 0; i < posts.size(); i++)
        {
            Post post = posts.get(i);
            int j = post.getId();
            if(j == id)
            {
                z = i;
            }
        }
        Post t = posts.get(z);
        posts.remove(t);
        writeToFile(posts);
    }

    private void writeToFile(List<Post> posts)
    {
        //Делает из листа строку
        String stringToWrite = gson.toJson(posts);
        File file = new File("resources/posts.json");
        FileWriter writer = null;
        try
        {
            writer = new FileWriter(file);
            writer.write(stringToWrite);
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private List<Post> getAllPostsInternal()
    {
        //Распаковывает файл
        FileReader file = null;
        try
        {
            file = new FileReader("resources/posts.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        List<Post> posts = gson.fromJson(file, new TypeToken<List<Post>>(){}.getType());
        return posts;
    }
}
