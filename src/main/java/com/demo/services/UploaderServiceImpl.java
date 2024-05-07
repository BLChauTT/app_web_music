package com.demo.services;

import com.demo.entities.Songdetail;
import com.demo.repositories.SongDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploaderServiceImpl implements UploaderService {
    @Autowired
    private SongDetailRepository songDetailRepository;

    @Override
    public List<Songdetail> findAudioByUploader(String uploader) {
        return null;
    }

    @Override
    public void songDetailAudio(Songdetail songdetail) {

    }

    @Override
    public Songdetail findSongDetailById(int id) {
        return null;
    }

    @Override
    public void deleteSongDetail(Songdetail songdetail) {

    }
}
