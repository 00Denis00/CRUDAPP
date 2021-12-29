package com.dsidorov.crudapp.repository;

import com.dsidorov.crudapp.controller.TagController;
import com.dsidorov.crudapp.model.Tag;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public enum TagStatus
{
    ACTIVE
    {
        public String printDirection()
        {
               String message = "Tag is active";
               return message;
        }
    },
    DELETED
    {
        public String printDirection()
        {
            String message = "Tag is deleted";
            return message;
        }
    };
}


