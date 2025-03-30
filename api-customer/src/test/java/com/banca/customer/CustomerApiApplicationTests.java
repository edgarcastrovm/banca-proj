package com.banca.customer;

import com.banca.customer.service.ClienteService;
import com.banca.utils.db.entity.Cliente;
import com.banca.utils.db.entity.Persona;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@EntityScan(basePackages = "com.banca.utils")
class CustomerApiApplicationTests {
	@Autowired
	private ClienteService clienteService;

	@Test
	public void testSaveClienteConPersonaInvalida() {
		Cliente cliente = new Cliente();
		cliente.setPersona(new Persona());
		Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			clienteService.save(cliente);
		});

		assertNull(cliente.getId());
		assertNull(cliente.getPersona().getId());
	}
}
