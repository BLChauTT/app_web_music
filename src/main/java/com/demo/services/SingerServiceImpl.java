package com.demo.services;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.entities.Singer;
import com.demo.entities.Song;
import com.demo.repositories.SingerRepository;
import com.demo.repositories.SongRepository;

@Service
public class SingerServiceImpl implements SingerService{

    @Autowired
    public SingerRepository singerRepository;
    @Autowired
    public SongRepository songRepository;
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
    public List<Singer> findByNameContainingIgnoreCase(String keyword) {
        return singerRepository.findByNameContainingIgnoreCase(keyword);
    }
    @Override
    public long countTotalSingers() {
        return singerRepository.count();
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
    @Override
	public Set<Singer> findSingersBySongId(int songId) {
        Song song = songRepository.findById(songId).orElse(null);
        if (song != null) {
            return song.getSingers();
        }
        return Collections.emptySet();
    }

    @Override
    public List<Singer> findSingersWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Singer> pageSingers = singerRepository.findAll(pageable);
        List<Singer> singers = pageSingers.getContent();
        return singers;
    }
}
