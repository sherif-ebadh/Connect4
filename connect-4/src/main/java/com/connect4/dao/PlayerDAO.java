package com.connect4.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.connect4.entity.Player;

@Transactional
public interface PlayerDAO extends CrudRepository<Player, Integer> {
	public Player findByFirstName(String firstName);
}