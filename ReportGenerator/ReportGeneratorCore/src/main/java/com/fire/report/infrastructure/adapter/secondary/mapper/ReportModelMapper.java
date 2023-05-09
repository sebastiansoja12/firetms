package com.fire.report.infrastructure.adapter.secondary.mapper;

import com.fire.report.domain.model.TruckPositionMessage;
import com.fire.report.infrastructure.adapter.secondary.entity.ReportEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ReportModelMapper {

    ReportEntity map(TruckPositionMessage truckPositionMessage);
}
