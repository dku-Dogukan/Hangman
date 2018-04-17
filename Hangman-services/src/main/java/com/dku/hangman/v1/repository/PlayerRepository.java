package com.dku.hangman.v1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dku.hangman.v1.domain.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

}
