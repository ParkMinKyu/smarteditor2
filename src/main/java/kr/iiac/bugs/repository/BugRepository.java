package kr.iiac.bugs.repository;

import kr.iiac.bugs.domain.Bug;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Long> {

}