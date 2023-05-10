package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.domain.model.BorderCrossing;
import com.fire.report.domain.port.secondary.BorderCrossRepository;
import com.fire.report.infrastructure.adapter.secondary.entity.BorderCrossEntity;
import com.fire.report.infrastructure.adapter.secondary.mapper.BorderModelMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BorderCrossRepositoryImpl implements BorderCrossRepository {

    private final BorderCrossReadRepository borderCrossReadRepository;

    private final BorderModelMapper borderModelMapper;
    @Override
    public void save(BorderCrossing borderCrossing) {
        final BorderCrossEntity borderCrossEntity = borderModelMapper.map(borderCrossing);
        borderCrossReadRepository.saveAndFlush(borderCrossEntity);
    }
}
