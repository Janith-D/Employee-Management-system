package com.example.imployeeMS.Repo;

import com.example.imployeeMS.Entity.Employee;
import com.example.imployeeMS.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team,Long> {
}
