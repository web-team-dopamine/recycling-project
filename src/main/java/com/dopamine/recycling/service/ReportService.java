package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.dto.ReportRequestDto;
import com.dopamine.recycling.domain.entity.Report;
import com.dopamine.recycling.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public Report save(ReportRequestDto report) {
        return reportRepository.save(report.toEntity());
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report getReportById(Long id) {
        return reportRepository.findById(id).orElse(null);
    }
}
