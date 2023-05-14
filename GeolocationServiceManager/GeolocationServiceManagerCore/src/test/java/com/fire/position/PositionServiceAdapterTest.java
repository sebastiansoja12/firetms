package com.fire.position;

import com.fire.position.domain.port.primary.PositionPort;
import com.fire.position.infrastructure.adapter.primary.PositionServiceAdapter;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionRequestMapper;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionRequestMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PositionServiceAdapterTest {

    private PositionServiceAdapter positionServiceAdapter;

    private final PositionRequestMapper positionRequestMapper = new PositionRequestMapperImpl();

    @Mock
    private PositionPort positionPort;


    @BeforeEach
    void setup() {
        positionServiceAdapter = new PositionServiceAdapter(positionRequestMapper, positionPort);
    }

    @Test
    void shouldGetNewestPosition() {
        // given&&when
        positionServiceAdapter.getNewestPosition(any());
        // then
        verify(positionPort, times(1)).determineNewestPosition(any());
    }
}
