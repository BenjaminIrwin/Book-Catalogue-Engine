package ic.doc;

import static ic.doc.QueryCreator.queryBooks;
import static org.junit.Assert.assertEquals;

import ic.doc.catalogues.Catalogue;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class BookSearchQueryTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Catalogue imperialCatalogue = context.mock(Catalogue.class);


  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    List<Book> books = List.of(new Book("Lord of the Rings", "Tolkein", 1937));

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("LASTNAME='dickens' "));
      will(returnValue(books));
    }});

    List<Book> result = queryBooks()
        .withSecondName("dickens")
        .build()
        .execute(imperialCatalogue);

    assertEquals(books, result);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstName() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("FIRSTNAME='Jane' "));
    }});

    queryBooks()
        .withFirstName("Jane")
        .build()
        .execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("TITLECONTAINS(Two Cities) "));
    }});

    queryBooks()
        .titleIncludes("Two Cities")
        .build()
        .execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("PUBLISHEDBEFORE(1700) "));
    }});

    queryBooks()
        .publishedBefore(1700)
        .build()
        .execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue)
          .searchFor(("PUBLISHEDAFTER(1950) "));
    }});

    queryBooks()
        .publishedAfter(1950)
        .build()
        .execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue)
          .searchFor(("LASTNAME='dickens' PUBLISHEDBEFORE(1840) "));
    }});


        queryBooks()
            .withSecondName("dickens")
            .publishedBefore(1840)
            .build()
            .execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue)
          .searchFor(("TITLECONTAINS(of) PUBLISHEDAFTER(1800) PUBLISHEDBEFORE(2000) "));
    }});

    queryBooks()
        .publishedBefore(2000)
        .publishedAfter(1800)
        .titleIncludes("of")
        .build()
        .execute(imperialCatalogue);

  }
}
