package com.dsidorov.crudapp.controller;

import com.dsidorov.crudapp.model.Post;
import com.dsidorov.crudapp.model.Writer;
import com.dsidorov.crudapp.repository.WriterRepository;

import java.util.List;

public class WriterController
{
    WriterRepository writerRepository = new WriterRepository(){};
    public void update(Writer writer)
    {
        writerRepository.update(writer);
    }
    public void deleteById(Integer id)
    {
        writerRepository.deleteById(id);
    }
    public String getById(Integer id)
    {
        String result = writerRepository.getById(id);
        return result;
    }
    public List<Writer> getAll()
    {
        List<Writer> writers = writerRepository.getAll();
        return writers;
    }
    public void save(Writer writer)
    {
        writerRepository.save(writer);
    }
}
