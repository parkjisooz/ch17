package market12;

public class CartItem {
	// private String[] itemBook = new String[8]; // 도서 정보의 개수
	private Book itemBook;
	private String bookID; // 도서 ID
	private int quantity; // 도서 수량
	private int totalPrice; // 도서 합계 가격

	public CartItem() {
	}

	/*
	 * public CartItem(String[] itemBook) { this.itemBook = itemBook; this.bookID =
	 * itemBook[0]; this.quantity = 1; updateTotalPrice(); } public String[]
	 * getItemBook() { return itemBook; } public void setItemBook(String[] itemBook)
	 * { this.itemBook = itemBook; }
	 */
	public CartItem(Book itemBook) {
		this.itemBook = itemBook;
		this.bookID = itemBook.getBookId();
		this.quantity = 1;
		updateTotalPrice();
	}

	public Book getItemBook() {
		return itemBook;
	}

	public void setItemBook(Book itemBook) {
		this.itemBook = itemBook;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
		this.updateTotalPrice();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalPrice();
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void updateTotalPrice() {
// totalPrice = Integer.parseInt(this.itemBook[3]) * this.quantity;
		totalPrice = this.itemBook.getUnitPrice() * this.quantity;
	}
}