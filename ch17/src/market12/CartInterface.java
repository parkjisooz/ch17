package market12;

import java.util.ArrayList;

public interface CartInterface {
	//void printBookList(Book[] p); // 전체 도서 정보 목록 출력
	void printBookList(ArrayList<Book> bookList) ;
	boolean isCartInBook(String id); // 장바구니에 담긴 개술르 1씩 증가
	void insertBook(Book p); // CartItem에 도서 정보를 등록
	void removeCart(int numId); // 장바구니 순번 numId의 항목을 삭제
	void deleteBook(); // 장바구니의 모든 항목을 삭제
	}