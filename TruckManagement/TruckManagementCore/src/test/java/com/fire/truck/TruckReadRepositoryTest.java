package com.fire.truck;

import com.fire.truck.infrastructure.adapter.secondary.TruckReadRepository;
import com.fire.truck.infrastructure.adapter.secondary.entity.TruckEntity;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = TruckTestConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TruckReadRepositoryTest {

    @Autowired
    private TruckReadRepository truckReadRepository;

    @Test
    @DatabaseSetup("/trucks.xml")
    void shouldFindTruckByPlate() {
        // given
        final String plate = "SR1234";
        // when
        final Optional<TruckEntity> truck = truckReadRepository.findByPlate(plate);
        // then
        Assertions.assertTrue(truck.isPresent());
    }

    @Test
    @DatabaseSetup("/trucks.xml")
    void shouldNotFindTruckByPlate() {
        // given
        final String plate = "SR4321";
        // when
        final Optional<TruckEntity> truck = truckReadRepository.findByPlate(plate);
        // then
        Assertions.assertTrue(truck.isEmpty());
    }

}
