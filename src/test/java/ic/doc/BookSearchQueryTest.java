package ic.doc;

import static ic.doc.QueryCreator.allTheBooks;

import ic.doc.catalogues.Catalogue;

import java.util.List;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;


//Create mock objects

public class BookSearchQueryTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Catalogue imperialCatalogue = context.mock(Catalogue.class);


  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("LASTNAME='dickens' "));
    }});

    List<Book> books = allTheBooks().withSecondName("dickens").build().execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstName() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("FIRSTNAME='Jane' "));
    }});

    List<Book> books = allTheBooks().firstName("Jane").build().execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("TITLECONTAINS(Two Cities) "));
    }});

    List<Book> books = allTheBooks().titleIncludes("Two Cities").build().execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("PUBLISHEDBEFORE(1700) "));
    }});

    List<Book> books = allTheBooks().publishedBefore(1700).build().execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("PUBLISHEDAFTER(1950) "));
    }});

    List<Book> books = allTheBooks().publishedAfter(1950).build().execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("LASTNAME='dickens' PUBLISHEDBEFORE(1840) "));
    }});

    List<Book> books = allTheBooks().withSecondName("dickens").publishedBefore(1840).build().execute(imperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    context.checking(new Expectations() {{
      oneOf(imperialCatalogue).searchFor(("TITLECONTAINS(of) PUBLISHEDAFTER(1800) PUBLISHEDBEFORE(2000) "));
    }});

    List<Book> books = allTheBooks().titleIncludes("of").publishedAfter(1800).publishedBefore(2000).build().execute(imperialCatalogue);

  }
}
