package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.entities.Song;
import com.demo.repositories.AccountSongRepository;
import com.demo.repositories.SongDetailRepository;
import com.demo.repositories.SongRepository;

@Service
public class SongServiceImpl implements SongService{

    @Autowired
    public SongRepository songRepository;
    
    @Autowired
    public SongDetailRepository songDetailRepository;
    
    @Autowired
    public AccountSongRepository accountSongRepository;

    @Override
    public boolean save(Song song) throws Exception {
        try {
            songRepository.save(song);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Song findSongById(int id) {
        for (Song song : findAll()) {
            if (song.getSongId() == id) {
                return song;
            }
        }
        return null;
    }
    @Override
    public Song updateSong(int id, Song song) {
        return null;
    }
    @Override
    public boolean deleteSong(int id) {  		
        try {
        	songDetailRepository.deleteBySongId(id);
            songRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
    }
    @Override
    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> getSongsByAuthor(int authorId) {
        return songRepository.findByAuthorId(authorId);
    }

    @Override
	public List<Song> findAllList() {
		return songRepository.findAll();
	}
	@Override
	public List<Song> findByTitle(String title) {
		return songRepository.findBySongDetail(title);
	}

	@Override
	public List<Song> findSongsWithPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Song> pageSongs = songRepository.findAll(pageable);
		List<Song> songs = pageSongs.getContent();
		return songs;
	}

}
