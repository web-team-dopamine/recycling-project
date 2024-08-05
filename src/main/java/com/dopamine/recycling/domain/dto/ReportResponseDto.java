package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Report;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportResponseDto {
    private Long id;
    private Long reporterId;
    private Long reporteeId;
    private String reason;
    private String explanation;

    public ReportResponseDto(Report report) {
        this.id = report.getId();
        this.reporterId = report.getReporterId();
        this.reporteeId = report.getReporteeId();
        this.reason = report.getReason();
        this.explanation = report.getExplanation();
    }
}
