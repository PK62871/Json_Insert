package com.mavencheck.repo;


import com.mavencheck.entity.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserWrapperRepository extends JpaRepository<UserWrapper, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM xyz WHERE user_details_json = CAST(:json AS jsonb))", nativeQuery = true)
    boolean existsByJson(@Param("json") String json);

}
