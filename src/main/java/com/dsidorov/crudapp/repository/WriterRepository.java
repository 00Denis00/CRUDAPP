package com.dsidorov.crudapp.repository;
import com.dsidorov.crudapp.model.Post;
import com.dsidorov.crudapp.model.Tag;
import com.dsidorov.crudapp.model.Writer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public interface WriterRepository
{
    Gson gson = new Gson();

    public default List<Writer> getAll() {
        //Выводит все элементы файла
        List<Writer> writers = getAllWritersInternal();
        return writers;
    }

    public default void save(Writer writer) {
        //Создает элемент
        List<Writer> writers = getAllWritersInternal();
        writers.add(writer);
        writeToFile(writers);
    }

    public default void update(Writer writer) {
        //Изменяет элемент
        List<Writer> writers = getAllWritersInternal();
        int id = writer.getId();
        int z = 0;
        for(int i = 0; i < writers.size(); i++)
        {
            Writer W = writers.get(i);
            int j = W.getId();
            if(j == id)
            {
                writers.remove(W);
                writers.add(writer);
            }
        }
        writeToFile(writers);
    }

    public default String getById(Integer id) {
        //Показывает элемент по ID
        List<Writer> writers = getAllWritersInternal();
        int z = 0;
        String name = "";
        String post = "";
        for(int i = 0; i < writers.size(); i++)
        {
            Writer T = writers.get(i);
            int j = T.getId();
            if(j == id)
            {
                name = T.getName();
                post = T.getPosts();
            }
        }
        String result = "Name: " + name + "\nPosts: " + post;
        return result;
    }

    public default void deleteById(Integer id) {
        //Удаляет элемент по ID
        int z = 0;
        List<Writer> writers = getAllWritersInternal();
        for(int i = 0; i < writers.size(); i++)
        {
            Writer writer = writers.get(i);
            int j = writer.getId();
            if(j == id)
            {
                z = i;
            }
        }
        Writer t = writers.get(z);
        writers.remove(t);
        writeToFile(writers);
    }

    private void writeToFile(List<Writer> writers)
    {
        //Делает из листа строку
        String stringToWrite = gson.toJson(writers);
        File file = new File("resources/writers.json");
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

    private List<Writer> getAllWritersInternal()
    {
        //Распаковывает файл
        FileReader file = null;
        try
        {
            file = new FileReader("resources/writers.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        List<Writer> writers = gson.fromJson(file, new TypeToken<List<Writer>>(){}.getType());
        return writers;
    }
}
