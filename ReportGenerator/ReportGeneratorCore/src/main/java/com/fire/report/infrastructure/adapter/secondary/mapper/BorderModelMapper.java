package com.fire.report.infrastructure.adapter.secondary.mapper;

import com.fire.report.domain.model.BorderCrossing;
import com.fire.report.domain.model.Event;
import com.fire.report.infrastructure.adapter.secondary.entity.BorderCrossEntity;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BorderModelMapper {

    BorderCrossEntity map(BorderCrossing borderCrossing);

}
