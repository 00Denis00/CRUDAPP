package com.dsidorov.crudapp.controller;

import com.dsidorov.crudapp.model.Tag;
import com.dsidorov.crudapp.repository.TagRepository;
import com.dsidorov.crudapp.repository.TagStatus;

import java.util.List;
import java.util.Scanner;

public class TagController {
    TagRepository tagRepository = new TagRepository(){};
    public String getById(Integer id)
    {
        String str = tagRepository.getById(id);
        return str;
    }
    public void deleteById(Integer id)
    {
        tagRepository.deleteById(id);
    }
    public String getAll()
    {
        String all = tagRepository.getAll();
        return all;
    }
    public TagStatus check(Integer id)
    {
        TagStatus T = tagRepository.check(id);
        return T;
    }
    public void save(Tag tag)
    {
        tagRepository.save(tag);
    }
    public void update(Tag tag)
    {
        tagRepository.update(tag);
    }
}
