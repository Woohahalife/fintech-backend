package com.core.backend.settlement.ui.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementRegisterRequest {

    private String settlementName;
    private Long totalAmount;
    private LocalDate groupingAt;
    private LocalDate settlementAt;
    private String settlementPlace;
    private List<SettlementParticipantRequest> participants;
}
