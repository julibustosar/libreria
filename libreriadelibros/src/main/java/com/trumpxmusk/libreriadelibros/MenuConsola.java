package com.trumpxmusk.libreriadelibros;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.trumpxmusk.libreriadelibros.model.Book;
import com.trumpxmusk.libreriadelibros.model.Person;
import com.trumpxmusk.libreriadelibros.service.BookService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MenuConsola {
    private final Scanner scanner = new Scanner(System.in);
    private boolean ejecutando = true;
    
    private final BookService bookService;

    public void iniciar() {
        while (ejecutando) {
            mostrarMenu();
            procesarOpcion(obtenerOpcion());
        }
    }

    private void mostrarMenu() {
        System.out.println("""
                Digite una opción
                1. Buscar libros por titulo
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar autores vivos en un determinado año
                5. Listar libros por idioma
                0. Salir 
                """);
        System.out.print("\nSeleccione una opción: ");
    }

    private int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 0:
                salir();
                break;
            case 1:
                System.out.print("\nIngrese el título del libro a buscar: ");
                String titulo = scanner.nextLine().trim();
                String formatTitulo = titulo.replace(" ", "%20");
                System.out.println(bookService.searchAndSaveBook(formatTitulo));
                break;
            case 2:
                listarLibrosRegistrados();
                break;
            case 3:
                listarAutoresRegistrados();
                break;
            case 4:
                listarAutoresVivos();
                break;
            case 5:
                listarLibrosPorIdioma();
                break;
            default:
                System.out.println("\nOpción no válida. Por favor, intente nuevamente.");
        }
    }

    private void listarLibrosRegistrados() {
        List<Book> libros = bookService.listarTodosLosLibros();
        if (libros.isEmpty()) {
            System.out.println("\nNo hay libros registrados en la base de datos.");
            return;
        }
        System.out.println("\nLibros registrados:");
        for (Book libro : libros) {
            System.out.println(libro);
        }
    }

    private void listarAutoresRegistrados() {
        List<Person> autores = bookService.listarTodosLosAutores();
        if (autores.isEmpty()) {
            System.out.println("\nNo hay autores registrados en la base de datos.");
            return;
        }
        System.out.println("\nAutores registrados:");
        for (Person autor : autores) {
            System.out.println(autor);
        }
    }

    private void listarAutoresVivos() {
        System.out.print("\nIngrese el año para ver autores vivos: ");
        try {
            int año = Integer.parseInt(scanner.nextLine().trim());
            List<Person> autores = bookService.listarAutoresVivosEnAño(año);
            if (autores.isEmpty()) {
                System.out.println("\nNo se encontraron autores vivos en el año " + año);
                return;
            }
            System.out.println("\nAutores vivos en el año " + año + ":");
            for (Person autor : autores) {
                System.out.println(autor);
            }
        } catch (NumberFormatException e) {
            System.out.println("\nPor favor, ingrese un año válido.");
        }
    }

    private void listarLibrosPorIdioma() {
        mostrarMenuIdiomas();
        int opcion = obtenerOpcion();
        String codigoIdioma = obtenerCodigoIdioma(opcion);
        
        if (codigoIdioma == null) {
            System.out.println("\nOpción de idioma no válida. Por favor, intente nuevamente.");
            return;
        }

        List<Book> libros = bookService.listarLibrosPorIdioma(codigoIdioma);
        if (libros.isEmpty()) {
            System.out.println("\nNo se encontraron libros en el idioma seleccionado.");
            return;
        }
        System.out.println("\nLibros en idioma '" + obtenerNombreIdioma(codigoIdioma) + "':");
        for (Book libro : libros) {
            System.out.println(libro);
        }
    }

    private void mostrarMenuIdiomas() {
        System.out.println("""
                \nSeleccione un idioma:
                1. Español (es)
                2. Inglés (en)
                3. Francés (fr)
                4. Portugués (pt)
                """);
        System.out.print("\nSeleccione una opción: ");
    }

    private String obtenerCodigoIdioma(int opcion) {
        return switch (opcion) {
            case 1 -> "es";
            case 2 -> "en";
            case 3 -> "fr";
            case 4 -> "pt";
            default -> "es";
        };
    }

    private String obtenerNombreIdioma(String codigo) {
        return switch (codigo) {
            case "es" -> "Español";
            case "en" -> "Inglés";
            case "fr" -> "Francés";
            case "de" -> "Alemán";
            case "it" -> "Italiano";
            case "pt" -> "Portugués";
            default -> codigo;
        };
    }

    private void salir() {
        System.out.println("\n👋 ¡Gracias por usar la aplicación!");
        ejecutando = false;
    }

    public void cerrar() {
        scanner.close();
    }
} 