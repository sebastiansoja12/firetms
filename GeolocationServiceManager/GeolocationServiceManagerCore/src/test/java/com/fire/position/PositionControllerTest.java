package com.fire.position;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.TruckPosition;
import com.fire.position.domain.port.primary.PositionPort;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class PositionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PositionPort positionPort;

    @Test
    void shouldInsertPosition() throws Exception {
        // given
        Position position = new Position();
        position.setVehicleReg("ABC123");
        position.setCountry("UK");

        // when
        doNothing().when(positionPort).insertPosition(position);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/positions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(position)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200));

        verify(positionPort, times(1)).insertPosition(position);
    }

    @Test
    void shouldGetPositionTruck() throws Exception {
        // given
        final String vehicleReg = "ABC123";
        int pageNumber = 1;
        int pageSize = 10;

        TruckPosition truckPosition = new TruckPosition(vehicleReg, Collections.emptyList());

        when(positionPort.getVehiclePosition(vehicleReg, pageNumber, pageSize)).thenReturn(truckPosition);

        // when
        mockMvc.perform(
                get("/positions/{vehicleReg}/{pageNumber}/{pageSize}", vehicleReg, pageNumber, pageSize))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.vehicleReg", is(vehicleReg)))
                .andExpect(jsonPath("$.positions", hasSize(0)));

        // then
        verify(positionPort, times(1)).getVehiclePosition(vehicleReg, pageNumber, pageSize);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}