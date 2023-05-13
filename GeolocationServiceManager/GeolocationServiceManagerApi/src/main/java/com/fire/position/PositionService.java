package com.fire.position;

import com.fire.position.dto.PositionDto;

import java.util.List;


public interface PositionService {

    void getNewestPosition(List<PositionDto> position);
}
