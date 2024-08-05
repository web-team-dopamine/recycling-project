package com.dopamine.recycling.controller;

import com.dopamine.recycling.domain.dto.ReportRequestDto;
import com.dopamine.recycling.domain.dto.ReportResponseDto;
import com.dopamine.recycling.domain.entity.Report;
import com.dopamine.recycling.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportResponseDto> report(@RequestBody ReportRequestDto request) {
        Report report = reportService.save(request);
        ReportResponseDto response = report.toResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<ReportResponseDto>> findAllReports() {
        List<Report> reportList = reportService.getAllReports();

        if(reportList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ReportResponseDto> responseList = reportList.stream()
                .map(ReportResponseDto::new)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDto> findReportById(@PathVariable("id") Long id) {
        Report report = reportService.getReportById(id);

        if(report == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ReportResponseDto(report));
    }
}
