package com.sommelier.wine4you.repository;

import com.sommelier.wine4you.model.Meal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);
}
