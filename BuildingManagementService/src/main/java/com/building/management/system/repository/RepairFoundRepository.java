package com.building.management.system.repository;

import com.building.management.system.model.entity.RepairFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairFoundRepository extends JpaRepository<RepairFund,Long> {
}
