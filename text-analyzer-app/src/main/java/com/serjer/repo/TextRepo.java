package com.serjer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serjer.domain.Text;

public interface TextRepo extends JpaRepository<Text, Long> {
	Text findOneByTextBody(String textBody);
}