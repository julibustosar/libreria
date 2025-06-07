package com.trumpxmusk.libreriadelibros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trumpxmusk.libreriadelibros.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {
} 