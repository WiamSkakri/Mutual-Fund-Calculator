package com.project.mutualfunds.repository;

import com.project.mutualfunds.model.MutualFundsDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutualFundRepository extends JpaRepository<MutualFundsDb,Long> {
}
