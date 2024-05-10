package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Author;
import com.demo.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    public AuthorRepository authorRepository;
    @Override
    public boolean save(Author author) throws Exception {
        try {
            authorRepository.save(author);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Author findAuthorById(int id) {
        for (Author author : findAll()) {
            if (author.getAuthorId() == id) {
                return author;
            }
        }
        return null;
    }

    @Override
    public Author findAuthorByKeyword(String keyword) {
    	return authorRepository.findAuthorByKeyword(keyword);
    }

    @Override
    public Author updateAuthor(int id, Author author) {
        return null;
    }

    @Override
    public void deleteAuthor(int id) {

    }

    @Override
    public List<Author> searchByKeyword(String keyword) {
        return null;
    }

    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }
}
