package com.salted_broccoli.cuillere.Repository;

import com.salted_broccoli.cuillere.Model.Calendar.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findTypeById(Long id);
}
