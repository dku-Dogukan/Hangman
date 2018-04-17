package com.dku.hangman.v1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dku.hangman.v1.domain.Game;
@Repository
public interface GameRepository extends CrudRepository<Game,Integer> {

}
