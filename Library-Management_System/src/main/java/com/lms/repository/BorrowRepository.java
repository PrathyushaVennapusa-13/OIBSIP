package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow,Integer > {

}
