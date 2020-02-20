package ic.doc;

public class QueryCreator {

  private String name1 = null;
  private String name2 = null;
  private String title = null;
  private Integer date1 = null;
  private Integer date2 = null;
;
 private QueryCreator() {};

 public static QueryCreator aQuery() {
   return new QueryCreator();
 }

 public BookSearchQuery search() {
   BookSearchQuery query = new BookSearchQuery(name1, name2, title, date1, date2);
   return query;
 }

  public QueryCreator firstName(String name) {
   name1 = name;
   return this;
  }

}
