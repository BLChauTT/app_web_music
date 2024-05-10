package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Singer;
import com.demo.repositories.SingerRepository;

@Service
public class SingerServiceImpl implements SingerService{

    @Autowired
    public SingerRepository singerRepository;
    @Override
    public boolean save(Singer singer) throws Exception {
        try {
            singerRepository.save(singer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Singer findSingerById(int id) {
        for (Singer singer : singerRepository.findAll()) {
            if (singer.getSingerId() == id) {
                return singer;
            }
        }
        return null;
    }

    @Override
    public Singer findSingerByKeyword(String keyword) {
        return null;
    }

    @Override
    public Singer updateSinger(int id, Singer singer) {
        return null;
    }

    @Override
    public void deleteSinger(int id) {

    }

    @Override
    public List<Singer> searchByKeyword(String keyword) {
        return null;
    }

    @Override
    public Iterable<Singer> findAll() {
        return singerRepository.findAll();
    }
}
