package com.connect4.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.connect4.entity.Game;

@Transactional
public interface GameDAO extends CrudRepository<Game, Integer> {

}