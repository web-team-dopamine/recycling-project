package com.dopamine.recycling.controller;

import com.dopamine.recycling.domain.dto.MarkRequestDto;
import com.dopamine.recycling.service.MarkService;
import com.dopamine.recycling.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mark")
@RequiredArgsConstructor
public class MarkController {
    private final MarkService markService;
    private final ProductService productService;

    @GetMapping("/add")
    public ResponseEntity<Void> addMark(@RequestBody MarkRequestDto request) {
        Long userId = 1L; // 임의 설정
        boolean successAddMark = markService.addMark(request.getProductId(), userId);

        return successAddMark ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/remove")
    public ResponseEntity<Void> removeMark(@RequestBody MarkRequestDto request) {
        Long userId = 1L;
        boolean successRemoveMark = markService.removeMark(request.getProductId(), userId);

        return successRemoveMark ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
