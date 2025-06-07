package com.trumpxmusk.libreriadelibros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LibreriadelibrosApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LibreriadelibrosApplication.class, args);
		
		// Obtener MenuConsola del contexto de Spring
		MenuConsola menu = context.getBean(MenuConsola.class);
		try {
			menu.iniciar();
		} finally {
			menu.cerrar();
		}
	}

}
