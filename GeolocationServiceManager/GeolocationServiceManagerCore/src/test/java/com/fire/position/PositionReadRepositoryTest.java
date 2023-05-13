package com.fire.position;

import com.fire.position.infrastructure.adapter.secondary.PositionReadRepository;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = PositionTestConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PositionReadRepositoryTest {

    @Autowired
    private PositionReadRepository positionReadRepository;

    @Test
    @DatabaseSetup("positions.xml")
    void shouldFindPositionByPlate() {
        // given
        final String plate = "SR1234";
        final Sort sort = Sort.by(Sort.Direction.DESC, "timestamp");
        // when
        final List<PositionEntity> positionEntity =
                positionReadRepository.findAllByVehicleReg(plate, sort);

        // then
        assertFalse(positionEntity.isEmpty());
    }

    @Test
    @DatabaseSetup("positions.xml")
    void shouldNotFindPositionByPlate() {
        // given
        final String plate = "abc";
        final Sort sort = Sort.by(Sort.Direction.DESC, "timestamp");
        // when
        final List<PositionEntity> positionEntity =
                positionReadRepository.findAllByVehicleReg(plate, sort);

        // then
        assertTrue(positionEntity.isEmpty());
    }

    @Test
    @DatabaseSetup("positions.xml")
    void shouldFindPositionByPlateAndReturnTrue() {
        // given
        final String plate = "SR1234";
        // when
        final boolean positionEntityExists = positionReadRepository.existsByVehicleReg(plate);

        // then
        assertTrue(positionEntityExists);
    }

    @Test
    @DatabaseSetup("positions.xml")
    void shouldFindPositionByPlateAndReturnFalse() {
        // given
        final String plate = "abc";
        // when
        final boolean positionEntityExists = positionReadRepository.existsByVehicleReg(plate);

        // then
        assertFalse(positionEntityExists);
    }
}
