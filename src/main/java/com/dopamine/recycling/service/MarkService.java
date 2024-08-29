package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.dto.MarkRequestDto;
import com.dopamine.recycling.domain.entity.Mark;
import com.dopamine.recycling.repository.MarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarkService {
    private final MarkRepository markRepository;

    @Transactional
    public boolean addMark(Long productId, Long userId) {
        Optional<Mark> existMark = markRepository.findByProductIdAndUserId(productId, userId);

        if(existMark.isPresent()) {
            return false;
        }

        MarkRequestDto request = new MarkRequestDto();
        request.setProductId(productId);
        request.setUserId(userId);

        markRepository.save(request.toEntity());
        return true;
    }

    @Transactional
    public boolean removeMark(Long productId, Long userId) {
        Optional<Mark> existMark = markRepository.findByProductIdAndUserId(productId, userId);

        if(existMark.isPresent()) {
            markRepository.delete(existMark.get());
            return true;
        } else {
            return false;
        }
    }
}
