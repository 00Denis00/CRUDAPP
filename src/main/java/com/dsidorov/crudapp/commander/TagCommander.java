package com.dsidorov.crudapp.commander;

import com.dsidorov.crudapp.controller.TagController;
import com.dsidorov.crudapp.model.Tag;
import com.dsidorov.crudapp.repository.TagStatus;

import java.util.List;
import java.util.Scanner;

public class TagCommander
{
    Scanner scanner = new Scanner(System.in);
    TagController tagController = new TagController();
    public void chech()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        TagStatus T = tagController.check(id);
        System.out.println(T);
        System.out.println();
    }
    public void getById()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        String str = tagController.getById(id);
        System.out.println("Tag: " + str);
        System.out.println();
    }
    public void deleteById()
    {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        tagController.deleteById(id);
        System.out.println("Tag " + id + " was deleted");
        System.out.println();
    }
    public void getAll()
    {
        String all = tagController.getAll();
        System.out.println(all);
        System.out.println();
    }
    public void save()
    {
        Tag tag = new Tag();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        tag.setName(name);
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        tag.setId(id);
        tagController.save(tag);
        System.out.println("Tag was saved");
        System.out.println();
    }
    public void update()
    {
        Tag tag = new Tag();
        System.out.println("Enter new name: ");
        String name = scanner.nextLine();
        tag.setName(name);
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        tag.setId(id);
        tagController.update(tag);
        System.out.println("Tag " + id + " was updated");
        System.out.println();
    }
}

