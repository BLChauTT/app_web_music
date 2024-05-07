package com.demo.services;

import java.util.List;

import com.demo.entities.Songdetail;

public interface UploaderService {
    public List<Songdetail> findAudioByUploader(String uploader);
    public void songDetailAudio(Songdetail songdetail);
    public Songdetail findSongDetailById(int id);
    public void deleteSongDetail(Songdetail songdetail);
}
