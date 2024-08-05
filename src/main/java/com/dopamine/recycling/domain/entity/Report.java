package com.dopamine.recycling.domain.entity;

import com.dopamine.recycling.domain.dto.ReportRequestDto;
import com.dopamine.recycling.domain.dto.ReportResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reporterId;
    private Long reporteeId;
    private String reason;
    private String explanation;

    public ReportResponseDto toResponse() {
        return ReportResponseDto.builder()
                .id(id)
                .reporterId(reporterId)
                .reporteeId(reporteeId)
                .reason(reason)
                .explanation(explanation)
                .build();
    }
}
