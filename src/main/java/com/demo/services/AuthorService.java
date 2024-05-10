package com.demo.services;

import java.util.List;

import com.demo.entities.Author;

public interface AuthorService {
    public boolean save(Author author) throws Exception;
    public Author findAuthorById(int id);
    public Author findAuthorByKeyword(String keyword);
    public Author updateAuthor(int id, Author author);
    public void deleteAuthor(int id);
    public List<Author> searchByKeyword(String keyword);
    public Iterable<Author> findAll();
}
