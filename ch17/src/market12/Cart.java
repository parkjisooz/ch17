package market12;

import java.util.ArrayList;

public class Cart implements CartInterface {
	// static final int NUM_BOOK = 3;
	// public CartItem[] cartItem = new CartItem[NUM_BOOK];
	public ArrayList<CartItem> cartItem = new ArrayList<CartItem>();
	public static int cartCount = 0;

	public Cart() {
	}

	@Override
	public void printBookList(ArrayList<Book> bookList) {
		for (int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			System.out.print(book.getBookId() + " | ");
			System.out.print(book.getIsbn() + " | ");
			System.out.print(book.getName() + " | ");
			System.out.print(book.getUnitPrice() + " | ");
			System.out.print(book.getAuthor() + " | ");
			System.out.print(book.getDescription() + " | ");
			System.out.print(book.getCategory() + " | ");
			System.out.print(book.getReleaseDate());
			System.out.println("");
		}
	}

	@Override
	public boolean isCartInBook(String bookId) {
		boolean flag = false;
		/*
		 * for (int i = 0; i < cartCount; i++) { if (bookId == cartItem[i].getBookID())
		 * { cartItem[i].setQuantity(cartItem[i].getQuantity() + 1); flag = true; } }
		 */
		for (int i = 0; i < cartItem.size(); i++) {
			if (bookId.equals(cartItem.get(i).getBookID())) {
				cartItem.get(i).setQuantity(cartItem.get(i).getQuantity() + 1);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void insertBook(Book book) {
		// cartItem[cartCount++] = new CartItem(book);
		CartItem bookItem = new CartItem(book);
		cartItem.add(bookItem);
		cartCount = cartItem.size();
	}

	@Override
	public void removeCart(int numId) {
		/*
		 * CartItem[] cartItem = new CartItem[NUM_BOOK]; int num = 0; for (int i = 0; i
		 * < cartCount; i++) { if (numId != i) { cartItem[num++] = cartItem[i]; } }
		 * cartCount = num; cartItem = cartItem;
		 */
		cartItem.remove(numId);
		cartCount = cartItem.size();
	}

	@Override
	public void deleteBook() {
		// cartItem = new CartItem[NUM_BOOK];
		cartItem.clear();
		cartCount = 0;
	}

	public void printCart() {
		System.out.println("장바구니 상품 목록 :");
		System.out.println("---------------------------------------------");
		System.out.println(" 도서 ID \t| 수량 \t| 합계");
		/*
		 * for (int i = 0; i < cartCount; i++) { System.out.print(" " +
		 * cartItem[i].getBookID() + " \t| "); System.out.print(" " +
		 * cartItem[i].getQuantity() + " \t| "); System.out.print(" " +
		 * cartItem[i].getTotalPrice()); System.out.println(" "); }
		 */
		for (int i = 0; i < cartItem.size(); i++) {
			System.out.print(" " + cartItem.get(i).getBookID() + " \t| ");
			System.out.print(" " + cartItem.get(i).getQuantity() + " \t| ");
			System.out.print(" " + cartItem.get(i).getTotalPrice());
			System.out.println(" ");
		}
		System.out.println("---------------------------------------------");
	}
}