package com.serjer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serjer.domain.LastLetter;

public interface LastLetterRepo extends JpaRepository<LastLetter, Long>{
	List<LastLetter> findAllByTextTextBody(String inputText);
}
