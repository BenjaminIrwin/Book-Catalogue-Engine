package ic.doc;

public class QueryCreator {

  private String name1 = null;
  private String name2 = null;
  private String title = null;
  private Integer date1 = null;
  private Integer date2 = null;


 private QueryCreator() {}

 public static QueryCreator queryBooks() {
   return new QueryCreator();
 }

 public BookSearchQuery build() {
   return new BookSearchQuery(name1, name2, title, date1, date2);
 }

  public QueryCreator withFirstName(String name) {
   name1 = name;
   return this;
  }

  public QueryCreator withSecondName(String name) {
    name2 = name;
    return this;
  }

  public QueryCreator titleIncludes(String titleIncludes) {
    title = titleIncludes;
    return this;
  }

  public QueryCreator publishedBefore(int publishedBefore) {
    date2 = publishedBefore;
    return this;
  }

  public QueryCreator publishedAfter(int publishedAfter) {
    date1 = publishedAfter;
    return this;
  }



}
