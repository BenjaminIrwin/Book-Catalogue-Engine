package ic.doc.catalogues;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Catalogue {

  List searchFor(String query);

}
