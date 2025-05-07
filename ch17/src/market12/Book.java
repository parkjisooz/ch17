package market12;

public class Book extends Item{
private String author; // 저자
private String description; // 설명
private String category; // 분류
private String releaseDate; // 출판일
public Book() {
}
public Book(String bookId, String isbn, String name, int unitPrice) {
super(bookId, isbn, name, unitPrice);
}
public Book(String bookId, String isbn, String name, int unitPrice, String author,
String description, String category, String releaseDate) {
super(bookId, isbn, name, unitPrice);
this.author = author;
this.description = description;
this.category = category;
this.releaseDate = releaseDate;
}
@Override
public String getBookId() {
return bookId;
}
@Override
public String getIsbn() {
return isbn;
}
@Override
public String getName() {
return name;
}
@Override
public int getUnitPrice() {
return unitPrice;
}
@Override
public void setBookId(String bookId) {
this.bookId = bookId;
}
@Override
public void setIsbn(String isbn) {
this.isbn = isbn;
}
@Override
public void setName(String name) {
this.name = name;
}
@Override
public void setUnitPrice(int unitPrice) {
this.unitPrice = unitPrice;
}
public String getAuthor() {
return author;
}
public void setAuthor(String author) {
this.author = author;
}
public String getDescription() {
return description;
}
public void setDescription(String description) {
this.description = description;
}
public String getCategory() {
return category;
}
public void setCategory(String category) {
this.category = category;
}
public String getReleaseDate() {
return releaseDate;
}
public void setReleaseDate(String releaseDate) {
this.releaseDate = releaseDate;
}
}