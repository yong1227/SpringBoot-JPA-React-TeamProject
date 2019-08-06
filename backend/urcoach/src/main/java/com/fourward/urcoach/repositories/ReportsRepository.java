package com.fourward.urcoach.repositories;

import com.fourward.urcoach.entities.Reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ReportRepository
 */
@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long>{

    public Reports findByReportDate(String reportDate);
    public void deleteByReportDate(String reportDate);
}