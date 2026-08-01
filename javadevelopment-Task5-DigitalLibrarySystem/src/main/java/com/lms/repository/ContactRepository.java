package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.Contact;
import com.lms.entity.User;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByUser(User user);
}
