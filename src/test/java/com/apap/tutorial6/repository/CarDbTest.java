package com.apap.tutorial6.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.apap.tutorial8.model.CarModel;
import com.apap.tutorial8.model.DealerModel;
import com.apap.tutorial8.repository.CarDb;

/**
 * CarDbTest
 * @author Zaki Raihan
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CarDbTest {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CarDb carDb;
	
	@Test
	public void whenFindByType_thenReturnCar() {
		// given
		DealerModel dealerModel = new DealerModel();
		dealerModel.setNama("Rendezvous");
		dealerModel.setAlamat("Elan Plateau");
		dealerModel.setNoTelp("0217382917");
		entityManager.persist(dealerModel);
		entityManager.persist(dealerModel);
		
		CarModel carModel = new CarModel();
		carModel.setBrand("MV Agusta");
		carModel.setType("F4 RC");
		carModel.setPrice(new Long("1500000000"));
		carModel.setAmount(13);
		carModel.setDealer(dealerModel);
		entityManager.persist(carModel);
		entityManager.flush();
		
		// when
		Optional<CarModel> found = carDb.findByType(carModel.getType());
		
		// then
		assertThat(found.get(), Matchers.notNullValue()); // Check if not null
		assertThat(found.get(), Matchers.equalTo(carModel)); // Check if same
		
	}
}
