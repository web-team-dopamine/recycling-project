package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Report;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportRequestDto {
    private Long reporterId;
    private Long reporteeId;
    private String reason;
    private String explanation;

    public Report toEntity() {
        return Report.builder()
                .reporterId(reporterId)
                .reporteeId(reporteeId)
                .reason(reason)
                .explanation(explanation)
                .build();
    }
}