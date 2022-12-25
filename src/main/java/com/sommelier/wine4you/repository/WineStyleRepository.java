package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.WineStyle;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineStyleRepository extends JpaRepository<WineStyle, Long> {
    Optional<WineStyle> findByNameStyle(String nameStyle);
}
