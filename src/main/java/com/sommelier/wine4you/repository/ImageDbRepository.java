package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.Image;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDbRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

    List<Image> findAllByWineId(Long wineId);
}
