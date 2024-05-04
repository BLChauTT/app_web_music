package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.AccountSong;

public interface AccountSongRepository extends JpaRepository<AccountSong, Integer> {

}
