package market12;

public abstract class Item {
String bookId; // 도서 ID
String isbn; // ISBN
String name; // 도서명
int unitPrice; // 도서 단가
public Item() {
}
public Item(String bookId, String isbn, String name, int unitPrice) {
this.bookId = bookId;
this.isbn = isbn;
this.name = name;
this.unitPrice = unitPrice;
}
public abstract String getBookId();
public abstract String getIsbn();
public abstract String getName();
public abstract int getUnitPrice();
public abstract void setBookId(String bookId);
public abstract void setIsbn(String isbn);
public abstract void setName(String name);
public abstract void setUnitPrice(int unitPrice);
}