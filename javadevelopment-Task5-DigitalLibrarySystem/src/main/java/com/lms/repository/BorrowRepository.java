package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.Borrow;
import com.lms.entity.User;
import com.lms.util.BorrowStatus;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

	  List<Borrow> findByUser(User user);

	    List<Borrow> findByStatus(BorrowStatus status);
	    List<Borrow> findByUserUserId(Integer userId);
}
