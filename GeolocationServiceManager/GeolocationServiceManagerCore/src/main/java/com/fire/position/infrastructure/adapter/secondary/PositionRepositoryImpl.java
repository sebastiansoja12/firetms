package com.fire.position.infrastructure.adapter.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import com.fire.position.infrastructure.adapter.secondary.exception.PositionNotFoundException;
import com.fire.position.infrastructure.adapter.secondary.mapper.PositionModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {

    private final PositionReadRepository positionReadRepository;

    private final PositionModelMapper positionModelMapper;

    @Override
    public List<Position> findPositionsByPlate(String plate, int pageSize, int pageNumber) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
         return positionReadRepository.findAllByVehicleReg(plate, pageable)
                .stream()
                .map(positionModelMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Position savePosition(Position position) {
        final PositionEntity positionEntity = positionModelMapper.map(position);
        return positionModelMapper.map(positionReadRepository.save(positionEntity));
    }

    @Override
    public Optional<PositionEntity> findPositionOnPlate(String plate) {
        final Sort sort = Sort.by(Sort.Direction.ASC, "timestamp");
        return positionReadRepository.findDistinctFirstByVehicleRegOrderByIdDesc(plate);
    }
}
