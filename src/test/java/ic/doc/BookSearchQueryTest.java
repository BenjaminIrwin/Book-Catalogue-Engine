package ic.doc;

import ic.doc.catalogues.Catalogue;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static ic.doc.QueryCreator.allTheBooks;

//Create mock objects

public class BookSearchQueryTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Catalogue ImperialCatalogue = context.mock(Catalogue.class);


  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    context.checking(new Expectations() {{
      oneOf(ImperialCatalogue).searchFor(("LASTNAME='dickens' "));
    }});

    List<Book> books = allTheBooks().withSecondName("dickens").build().execute(ImperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstName() {

    context.checking(new Expectations() {{
      oneOf(ImperialCatalogue).searchFor(("FIRSTNAME='Jane' "));
    }});

    List<Book> books = allTheBooks().firstName("Jane").build().execute(ImperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    context.checking(new Expectations() {{
      oneOf(ImperialCatalogue).searchFor(("TITLECONTAINS(Two Cities) "));
    }});

    List<Book> books = allTheBooks().titleIncludes("Two Cities").build().execute(ImperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    context.checking(new Expectations() {{
      oneOf(ImperialCatalogue).searchFor(("PUBLISHEDBEFORE(1700) "));
    }});

    List<Book> books = allTheBooks().publishedBefore(1700).build().execute(ImperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    context.checking(new Expectations() {{
      oneOf(ImperialCatalogue).searchFor(("PUBLISHEDAFTER(1950) "));
    }});

    List<Book> books = allTheBooks().publishedAfter(1950).build().execute(ImperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    context.checking(new Expectations() {{
      oneOf(ImperialCatalogue).searchFor(("LASTNAME='dickens' PUBLISHEDBEFORE(1840) ")); //OR?!?!?!?!?!?!?
    }});

    List<Book> books = allTheBooks().withSecondName("dickens").publishedBefore(1840).build().execute(ImperialCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    context.checking(new Expectations() {{
      oneOf(ImperialCatalogue).searchFor(("TITLECONTAINS(of) PUBLISHEDAFTER(1800) PUBLISHEDBEFORE(2000) "));
    }});

    List<Book> books = allTheBooks().titleIncludes("of").publishedAfter(1800).publishedBefore(2000).build().execute(ImperialCatalogue);

  }
}
