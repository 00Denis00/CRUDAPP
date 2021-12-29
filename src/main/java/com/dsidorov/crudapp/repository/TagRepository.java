package com.dsidorov.crudapp.repository;

import com.dsidorov.crudapp.model.Tag;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public interface TagRepository
{
    final Gson gson = new Gson();
    final String FILE_PATH = "C:\\Users\\sdden\\Desktop\\Программирование\\crudapp\\tags.json";
    TagStatus deleted = TagStatus.DELETED;
    TagStatus active = TagStatus.ACTIVE;

    public default TagStatus check(Integer id) {
        //Проверяет элемент по ID
        int z = -100;
        List<Tag> tags = getAllTagsInternal();
        for(int i = 0; i < tags.size(); i++)
        {
            Tag tag = tags.get(i);
            int j = tag.getId();
            if(j == id)
            {
                z = i;
            }
        }
        if(z == -100)
        {
            return deleted;
        }
        else
        {
            return active;
        }
    }

    public default String getById(Integer id) {
        //Показывает элемент по ID
        int z = 0;
        List<Tag> tags = getAllTagsInternal();
        for(int i = 0; i < tags.size(); i++)
        {
            Tag tag = tags.get(i);
            int j = tag.getId();
            if(j == id)
            {
                z = i;
            }
        }
        Tag t = tags.get(z);
        String name = t.getName();
        return name;
    }

    public default String getAll() {
        //Выводит все элементы файла
        List<Tag> tags = getAllTagsInternal();
        Integer x = tags.size();
        String str = gson.toJson(tags);
        char[] result = str.toCharArray();
        for(int i = 0; i < result.length; i++)
        {
            if(result[i] == '[' || result[i] == ']' || result[i] == '}')
            {
                result[i] = ' ';
            }
            else if(result[i] == '{')
            {
                result[i] = '\n';
            }
        }
        String all = new String(result);
        return all;
    }

    public default void save(Tag tag) {
        //Создает элемент
        List<Tag> tags = getAllTagsInternal();
        tags.add(tag);
        writeToFile(tags);
    }

    public default void update(Tag tag) {
        //Изменяет элемент
        List<Tag> tags = getAllTagsInternal();
        int id = tag.getId();
        int z = 0;
        for(int i = 0; i < tags.size(); i++)
        {
            Tag T = tags.get(i);
            int j = tag.getId();
            if(j == id)
            {
                z = i;
            }
        }
        Tag t = tags.get(z);
        tags.remove(t);
        writeToFile(tags);
        tags.add(tag);
        writeToFile(tags);
    }

    public default void deleteById(Integer id) {
        //Удаляет элемент по ID
        int z = 0;
        List<Tag> tags = getAllTagsInternal();
        for(int i = 0; i < tags.size(); i++)
        {
            Tag tag = tags.get(i);
            int j = tag.getId();
            if(j == id)
            {
                z = i;
            }
        }
        Tag t = tags.get(z);
        tags.remove(t);
        writeToFile(tags);
    }


    private Integer generateNewMaxId(List<Tag> tags) {
        //Берёт последнее ID в файле
        Tag tagWithMaxId = tags.stream().max(Comparator.comparing(Tag::getId)).orElse(null);
        return Objects.nonNull(tagWithMaxId) ? tagWithMaxId.getId() : 1;
    }

    private void writeToFile(List<Tag> tags) {
        //Делает из листа строку
        String stringToWrite = gson.toJson(tags);
        File file = new File("resources/tags.json");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(stringToWrite);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Tag> getAllTagsInternal() {
        //Распаковывает файл
        FileReader file = null;
        try
        {
            file = new FileReader("resources/tags.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        List<Tag> tags = gson.fromJson(file, new TypeToken<List<Tag>>() {}.getType());
        return tags;
    }
}
