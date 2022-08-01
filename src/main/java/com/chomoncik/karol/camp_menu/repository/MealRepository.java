package com.chomoncik.karol.camp_menu.repository;

import com.chomoncik.karol.camp_menu.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
