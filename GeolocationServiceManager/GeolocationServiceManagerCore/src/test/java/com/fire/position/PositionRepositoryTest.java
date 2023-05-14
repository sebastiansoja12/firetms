package com.fire.position;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = PositionTestConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DatabaseSetup("classpath:positions.xml")
public class PositionRepositoryTest {

    @Autowired
    private PositionRepository positionRepository;

    @Test
    void shouldFindPositionByPlate() {
        // given
        final String plate = "SR1234";
        final int pageNumber = 1;
        final int pageSize = 1;

        // when
        final List<Position> positions =
                positionRepository.findPositionsByPlate(plate, pageNumber, pageSize);

        // then
        assertFalse(positions.isEmpty());
    }

    @Test
    void shouldNotFindPositionByPlate() {
        // given
        final String plate = "abc";
        final int pageNumber = 1;
        final int pageSize = 1;
        // when
        final List<Position> positions =
                positionRepository.findPositionsByPlate(plate, pageNumber, pageSize);

        // then
        assertTrue(positions.isEmpty());
    }

    @Test
    void shouldFindPositionByPlateAndReturnTrue() {
        // given
        final String plate = "SR1234";
        // when
        final Optional<PositionEntity> positionEntity = positionRepository.findPositionOnPlate(plate);
        // then
        assertTrue(positionEntity.isPresent());
    }

    @Test
    void shouldFindPositionByPlateAndReturnFalse() {
        // given
        final String plate = "abc";
        // when
        final Optional<PositionEntity> positionEntity = positionRepository.findPositionOnPlate(plate);
        // then
        assertTrue(positionEntity.isEmpty());
    }

}
