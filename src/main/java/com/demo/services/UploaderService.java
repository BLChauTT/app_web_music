package com.demo.services;

import com.demo.entities.Songdetail;

import java.util.List;

public interface UploaderService {
    public List<Songdetail> findAudioByUploader(String uploader);
    public void songDetailAudio(Songdetail songdetail);
    public Songdetail findSongDetailById(int id);
    public void deleteSongDetail(Songdetail songdetail);
}
