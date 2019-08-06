package com.fourward.urcoach.controller;

import java.util.HashMap;

import com.fourward.urcoach.entities.Reports;
import com.fourward.urcoach.repositories.ReportsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReportsController
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/report")
public class ReportsController {

    @Autowired
    private ReportsRepository repo;

    @PostMapping("")
    public HashMap<String, String> postReport(@RequestBody Reports reports) {
        HashMap<String, String> map = new HashMap<>();
        repo.save(reports);
        map.put("RESULT", "리포트 등록 성공");
        return map;
    }

    @GetMapping("/find/{reportDate}")
    public Reports findByReportDate(@PathVariable String reportDate){
        return repo.findByReportDate(reportDate);
    }

    @PutMapping("/update/{reportDate}")
    public HashMap<String, String> updateReport(@PathVariable String reportDate, @RequestBody Reports reports){
        HashMap<String, String> map = new HashMap<>();
        Reports tempReports = repo.findByReportDate(reportDate);
        tempReports.setReportCalIn(reports.getReportCalIn());
        tempReports.setReportCalOut(reports.getReportCalOut());
        repo.save(tempReports);
        map.put("RESULT", "리포트 수정 성공");
        return map;
    }

    @DeleteMapping("/delete/{reportId}")
    public HashMap<String, String> deleteReport(@PathVariable String reportId){
        HashMap<String, String> map = new HashMap<>();
        repo.deleteById(Long.parseLong(reportId));
        map.put("RESULT", "리포트 삭제 성공");
        return map;
    }
}