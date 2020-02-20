package ic.doc;

import static ic.doc.QueryCreator.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

public class BookSearchQueryTest {

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    List<Book> books = allTheBooks().withSecondName("dickens").build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstName() {

    List<Book> books = allTheBooks().firstName("Jane").build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books = allTheBooks().titleIncludes("Two Cities").build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = allTheBooks().publishedBefore(1700).build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = allTheBooks().publishedAfter(1950).build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = allTheBooks().withSecondName("dickens").publishedBefore(1840).build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    List<Book> books = allTheBooks().titleIncludes("of").publishedAfter(1800).publishedBefore(2000).build().execute();

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
