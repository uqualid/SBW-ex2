package com.example.ex2.Repository;

import com.example.ex2.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> { //could use only extension
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to); // sort Memo desc(from - to)
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable); // Pageable + Query Method
    void deleteMemoByMnoLessThan(Long num); // deleteBy + Query Method - on condition
}
