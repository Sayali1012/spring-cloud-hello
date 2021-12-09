package com.sayali.springcloudhello.repository;

import com.sayali.springcloudhello.model.Contact;
// import com.sayali.springcloudhello.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> { }
