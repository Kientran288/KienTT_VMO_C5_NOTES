package com.note.demo.repository;

import com.note.demo.models.Checkbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckboxRepository extends JpaRepository<Checkbox, Long> {

}
