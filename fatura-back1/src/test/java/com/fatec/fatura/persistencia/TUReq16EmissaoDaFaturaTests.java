package com.fatec.fatura.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.fatec.fatura.model.Fatura;

class TUReq16EmissaoDaFaturaTests {
	Logger logger = LogManager.getLogger(this.getClass());
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	Fatura fatura;

	@Test
	void ct01_quando_dados_validos_fatura_nao_eh_nulo() {
		try {
			// dado que as informacoes de fatura sao validas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("39086360009", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje
			assertNotNull(fatura);
			assertFalse(fatura.isCancelada());
			assertFalse(fatura.isPaga());
		} catch (Exception e) {
			logger.info(">>>>>> ct01 - nao deveria falhar => " + e.getMessage());
			fail("nao deveria falhar fatura valida");

		}

	}

	@Test
	void ct01_quando_dados_invalidos_cpf_vazio_mensagem_de_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("", dataVencimento, "moveis planejados", "1000.50");
			// entao retorna mensagem de erro
		} catch (Exception e) {
			logger.info(">>>>>> ct02 - mensagem => " + e.getMessage());
			assertEquals("CPF invalido", e.getMessage());
		}

	}

	@Test
	void ct03_quando_dados_invalidos_cpf_branco_msg_de_erro() {
		try {
			// dado que as informacoes de fatura sao validas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2025", formatter);
			fatura = new Fatura(" ", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje

		} catch (Exception e) {
			logger.info(">>>>>> ct01 - nao deveria falhar => " + e.getMessage());
			assertEquals("CPF invalido", e.getMessage());

		}

	}

	@Test
	void ct04_quando_dados_invalidos_cpf_null_msg_de_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura(null, dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje

		} catch (Exception e) {
			logger.info(">>>>>> ct01 - nao deveria falhar => " + e.getMessage());
			assertEquals("CPF invalido", e.getMessage());

		}

	}

	@Test
	void ct05_quando_dados_invalidos_cpf_formatado_msg_de_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("390.863.600-09", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje

		} catch (Exception e) {
			logger.info(">>>>>> ct01 - nao deveria falhar => " + e.getMessage());
			assertEquals("CPF invalido", e.getMessage());

		}

	}

	@Test
	void ct06_quando_dados_invalidos_cpf_caractere_iguais_msg_de_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("111.111.111-11", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje

		} catch (Exception e) {
			logger.info(">>>>>> ct01 - nao deveria falhar => " + e.getMessage());
			assertEquals("CPF invalido", e.getMessage());

		}

	}

	@Test
	void ct07_quando_dados_invalidos_cpf_12_caracteres_msg_de_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("111.111.111-112", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje

		} catch (Exception e) {
			logger.info(">>>>>> ct01 - nao deveria falhar => " + e.getMessage());
			assertEquals("CPF invalido", e.getMessage());

		}

	}

	@Test
	void ct08_quando_dados_invalidos_cpf_invalido_msg_de_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("123.456.789-11", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje

		} catch (Exception e) {
			logger.info(">>>>>> ct01 - nao deveria falhar => " + e.getMessage());
			assertEquals("CPF invalido", e.getMessage());

		}

	}

	@Test
	void ct09_quando_data_invalida_msg_de_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("31/02/2026", formatter);
			fatura = new Fatura("3a086360009", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje
			logger.info(">>>>>> data de vencimento " + dataVencimento.toString());
			assertNotNull(fatura);
		} catch (Exception e) {
			logger.info(">>>>>> ct09 - nao deveria falhar => " + e.getMessage());
			fail("nao deveria falhar fatura valida");

		}

	}

	@Test
	void ct10_quando_data_vencimento_ja_passou_msg_de_erro() {
		try {
			LocalDate dataVencimento = LocalDate.parse("02/01/2020", formatter); // data no passado
			fatura = new Fatura("39086360009", dataVencimento, "moveis planejados", "1000.50");
			logger.info(">>>>>> data de vencimento " + dataVencimento.toString());
		} catch (Exception e) {
			logger.info(">>>>>> ct10 - nao deveria falhar => " + e.getMessage());
		}

	}

	void ct11_quando_valor_da_fatura_zerado_ou_negativo_msg_de_erro() {
		try {
			// dado que o valor informado da fatura é inválido (zerado ou negativo)
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);

			// caso 1: valor zerado
			fatura = new Fatura("39086360009", dataVencimento, "moveis planejados", "0.00");
			logger.info(">>>>>> ct11 - deveria ter falhado com valor zerado");
			fail("nao deveria permitir fatura com valor zerado");

			// caso 2: valor negativo
			fatura = new Fatura("39086360009", dataVencimento, "moveis planejados", "-100.00");
			logger.info(">>>>>> ct11 - deveria ter falhado com valor negativo");
			fail("nao deveria permitir fatura com valor negativo");

		} catch (Exception e) {
			logger.info(">>>>>> ct11 - mensagem => " + e.getMessage());
			assertEquals("Valor da fatura invalido", e.getMessage());
		}
	}
}