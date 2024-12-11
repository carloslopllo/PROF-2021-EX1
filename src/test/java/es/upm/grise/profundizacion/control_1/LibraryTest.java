package es.upm.grise.profundizacion.control_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
	private Library l;

	@BeforeEach
	public void setUp() {
        l = new Library();
    }
	
	@Test
	public void addBookA() {
		Book b1 = new Book("a");
		try {
			l.addBook(b1);
		} catch (DuplicatedBookException e) {
			e.printStackTrace();
		}

		Book bc = null;
		try {
			bc = l.getBook("a");
		} catch (NonExistingBookException e) {
			e.printStackTrace();
		} catch (EmptyLibraryException e) {
			e.printStackTrace();
		}

		assertEquals(b1, bc);
	}

	@Test
	public void addBookB() {
		Book b1 = new Book("a");
		try {
			l.addBook(b1);
		} catch (DuplicatedBookException e) {
			e.printStackTrace();
		}

		assertThrows(DuplicatedBookException.class, () -> {
			l.addBook(b1);
		});
	}

}
