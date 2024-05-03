package com.demo.services;

import java.util.List;

import com.demo.entities.Song;
import com.demo.entities.Songdetail;

public interface SongDetailService {
    public Iterable<Songdetail> findAll();
}
