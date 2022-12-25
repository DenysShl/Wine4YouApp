package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineImageRepository extends JpaRepository<Image, Long> {
}
