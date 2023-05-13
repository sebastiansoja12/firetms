package com.fire.position.infrastructure.adapter.primary;

import com.fire.position.PositionService;
import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.primary.PositionPort;
import com.fire.position.dto.PositionDto;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionRequestMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PositionServiceAdapter implements PositionService {

    private final PositionRequestMapper positionRequestMapper;

    private final PositionPort positionPort;

    @Override
    public void getNewestPosition(List<PositionDto> position) {
       final List<Position> positions = positionRequestMapper.map(position);
       positionPort.determineNewestPosition(positions);
    }
}
