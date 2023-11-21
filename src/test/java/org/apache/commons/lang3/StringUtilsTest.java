package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringUtilsTest {
	@Test
	void stripAccentsTest() {
		assertEquals("Agua de Chuva no Mar", StringUtils.stripAccents("Água de Chuva no Mar"));
		assertEquals("E Hoje", StringUtils.stripAccents("É Hoje"));
	}
}
