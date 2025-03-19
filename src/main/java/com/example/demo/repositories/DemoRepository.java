package com.example.demo.repositories;

import com.example.demo.domain.Average;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<Average, Long>, PagingAndSortingRepository<Average, Long> {
}
