package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.Reservation;
import com.lms.entity.User;
import com.lms.util.ReservationStatus;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	List<Reservation> findByStatus(ReservationStatus status);

	List<Reservation> findByUserUserId(Integer userId);
}
