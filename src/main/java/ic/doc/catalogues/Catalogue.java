package ic.doc.catalogues;

import ic.doc.Book;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Catalogue {

  List<Book> searchFor(String query);

}
