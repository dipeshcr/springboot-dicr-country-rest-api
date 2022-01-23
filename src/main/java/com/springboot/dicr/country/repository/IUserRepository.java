package com.springboot.dicr.country.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.dicr.country.model.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(final String email);

	Optional<UserEntity> findByUserNameOrEmail(final String userName, final String emial);

	Optional<UserEntity> findByUserName(String userName);
	
	boolean existsByUserName(final String userName);
	
	boolean existsByEmail(final String email);
}
