package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.WineTaste;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineTasteRepository extends JpaRepository<WineTaste, Long> {
    Optional<WineTaste> findByNameTaste(String nameTaste);
}
