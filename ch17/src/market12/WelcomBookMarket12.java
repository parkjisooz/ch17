package market12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import market.Admin;
import market.CartItem;

public class WelcomBookMarket12 {
	// static final int NUM_BOOK = 3; // 도서 개수
	// static final int NUM_ITEM = 8; // 도서 정보의 개수
	static Cart cart = new Cart();
	static User user; // 사용자

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		String userName; // 고객 이름
		String userMobile; // 연락처
		int numberSelection; // 메뉴 번호 선택
		// Book[] bookInfoList = new Book[NUM_BOOK];
		// Book[] bookInfoList; // 도서 정보를 저장하기 위한 배열 선언
		ArrayList<Book> bookInfoList;
		int totalBookCount = 0; // 도서 총 개수를 저장
		System.out.println("Book Market 고객 정보 입력");
		System.out.print("고객의 이름을 입력하세요 : ");
		userName = input1.nextLine();
		System.out.print("연락처를 입력하세요 : ");
		userMobile = input.nextLine();
		// 사용자 정보 저장
		user = new User(userName, userMobile);
		boolean quit = false; // 무한 반복
		while (!quit) {
			menuintroduction();
			try {
				System.out.print("메뉴 번호를 선택해주세요 : ");
				numberSelection = input.nextInt();
				if (numberSelection < 1 || numberSelection > 9) {
					System.out.println("1부터9까지의 숫자를 입력하세요.");
				} else {
					switch (numberSelection) {
					case 1:
						menuGuestInfo(userName, userMobile);

						break;
					case 2:
						menuCartItemList();

						break;
					case 3:
						menuCartClear();

						break;
					case 4:
						// System.out.println("4. 장바구니에 항목 추가하기 : ");
						// menuCartAddItem(bookInfoList);

						totalBookCount = totalFileToBookList(); // 도서 개수
						// bookInfoList = new Book[totalBookCount]; // 도서 개수에

						bookInfoList = new ArrayList<Book>();
						menuCartAddItem(bookInfoList);
						break;
					case 5:
						menuCartRemoveItemCount();

						break;
					case 6:
						menuCartRemoveItem();

						break;
					case 7:
						menuCartBill();

						break;
					case 8:
						menuExit();

						quit = true; // 종료

						break;
					case 9:
						menuAdminLogin();

						break;
					}
				}
			} catch (CartException e) {
				System.out.println(e.getMessage());
				quit = true;
			} catch (Exception e) {
				System.out.println("잘못된 메뉴 선택으로 종료합니다.");
				quit = true;
			}
		}
	}

	private static void menuintroduction() {
		System.out.println("**********************************************");
		System.out.println("\t\t" + "Book Market Menu");
		System.out.println("**********************************************");
		System.out.println(" 1. 고객 정보 확인하기 \t4. 장바구니에 항목 추가하기");
		System.out.println(" 2. 장바구니 상품 목록 보기\t5. 장바구니의 항목 수량 줄이기");
		System.out.println(" 3. 장바구니 비우기     \t6. 장바구니의 항목 삭제하기");
		System.out.println(" 7. 영수증 표시하기      \t8. 종료");
		System.out.println(" 9. 관리자 로그인");
		System.out.println("***********************************************");
	}

	public static void menuGuestInfo(String name, String mobile) {
		System.out.println("현재 고객 정보 : ");
		System.out.println("이름 : " + user.getName() + ", 연락처 : " + user.getPhone());
	}

	public static void menuCartItemList() {
		if (cart.cartCount >= 0) {
			cart.printCart();
		}
	}

	public static void menuCartClear() throws CartException {
		// System.out.println("장바구니 비우기");
		if (cart.cartCount == 0) {
			// System.out.println("장바구니에 항목이 없습니다");
			throw new CartException("장바구니에 항목이 없습니다");
		} else {
			System.out.println("장바구니에 모든 항목을 삭제하겠습니까? Y | N ");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			if (str.toUpperCase().equals("Y") || str.toLowerCase().equals("y")) {
				System.out.println("장바구니에 모든 항목을 삭제했습니다");
				cart.deleteBook();
			}
		}
	}

	public static void menuCartAddItem(ArrayList<Book> book) {
		// System.out.println("장바구니에 항목 추가하기 : ");
		bookList(book); // 도서 정보가 저장되어 있는 메서드 호출
		cart.printBookList(book);
		boolean quit = false;
		while (!quit) {
			Scanner input = new Scanner(System.in);
			System.out.print("장바구니에 추가할 도서의 ID를 입력하세요 :");
			String inputStr = input.nextLine();
			boolean flag = false; // 일치 여부
			int numId = -1; // 인덱스 번호
			for (int i = 0; i < book.size(); i++) {
				// 입력한 도서ID와 저장되어 이쓴 도서 정보의 ID가 일치하면
				// 인덱스 번호와 일치 여부 변수의 값을 변경한다.
				if (inputStr.equals(book.get(i).getBookId())) {
					numId = i;
					flag = true;
					break;
				}
			}
			// 일치하면 장바구니 추가 여부를 묻는다.
			if (flag) {
				System.out.println("장바구니에 추가하겠습니까? Y | N ");
				inputStr = input.nextLine();
				if (inputStr.toUpperCase().equals("Y") || inputStr.toLowerCase().equals("y")) {
					System.out.println(book.get(numId).getBookId() + " 도서가 장바구니에추가되었습니다.");
					// 장바구니에 넣기
					if (!isCartInBook(book.get(numId).getBookId())) {
						// cartItem[cartCount++] = new CartItem(book[numId]);

						cart.insertBook(book.get(numId));

					}
				}
				quit = true;
			} else {
				System.out.println("다시 입력해 주세요");
			}
		}
	}

	public static void menuCartRemoveItemCount() throws CartException {
		System.out.println("5. 장바구니의 항목 수량 줄이기");
		Scanner input = new Scanner(System.in);
		if (cart.cartCount == 0) {
			throw new CartException("장바구니에 항목이 없습니다");
		} else {
			menuCartItemList();
			boolean quit = false;
			while (!quit) {
				boolean flag = false;
				int numId = -1;
				System.out.print("장바구니에서 항목 수량 줄일 도서의 ID를 입력하세요 :");
				String str = input.nextLine();
				for (int i = 0; i < cart.cartCount; i++) {
					if (str.equals(cart.cartItem.get(i).getBookID())) {
						numId = i;
						flag = true;
						break;
					}
				}
				if (flag) {
					System.out.println("장바구니의 수량(-1)을 줄이시겠습니까? Y | N ");
					str = input.nextLine();
					int numIdCount;
					if (str.toUpperCase().equals("Y") || str.toLowerCase().equals("y")) {
						numIdCount = cart.cartItem.get(numId).getQuantity() - 1;
						cart.cartItem.get(numId).setQuantity(numIdCount);
						System.out.println(cart.cartItem.get(numId).getBookID() + "도서의 수량을 -1개 줄였습니다.");
					}

					quit = true;
				} else {
					System.out.println("다시 입력해 주세요");
				}
			}
		}
	}

	public static void menuCartRemoveItem() throws CartException {
		// System.out.println("6. 장바구니의 항목 삭제하기");
		if (cart.cartCount == 0) {
			// System.out.println("장바구니에 항목이 없습니다");
			throw new CartException("장바구니에 항목이 없습니다");
		} else {
			menuCartItemList();
			boolean quit = false;
			while (!quit) {
				System.out.print("장바구니에서 삭제할 도서의 ID 를 입력하세요 :");
				Scanner input = new Scanner(System.in);
				String str = input.nextLine();
				boolean flag = false;
				int numId = -1;
				for (int i = 0; i < cart.cartCount; i++) {
					if (str.equals(cart.cartItem.get(i).getBookID())) {
						numId = i;
						flag = true;
						break;
					}
				}
				if (flag) {
					System.out.println("장바구니의 항목을 삭제하겠습니까? Y | N ");
					str = input.nextLine();
					if (str.toUpperCase().equals("Y") || str.toLowerCase().equals("y")) {
						System.out.println(cart.cartItem.get(numId).getBookID() + "도서가 장바구니에서 삭제되었습니다.");
						cart.removeCart(numId);
					}

					quit = true;
				} else {
					System.out.println("다시 입력해 주세요");
				}
			}
		}
	}

	public static void menuCartBill() throws CartException {
		// System.out.println("7. 영수증 표시하기");
		if (cart.cartCount == 0) {
			// System.out.println("장비구니에 항목이 없습니다");
			throw new CartException("장바구니에 항목이 없습니다");
		} else {
			System.out.println("배송받을 분은 고객정보와 같습니까? Y | N ");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			if (str.toUpperCase().equals("Y") || str.toLowerCase().equals("y")) {
				System.out.print("배송지를 입력해주세요 ");
				String address = input.nextLine();
				// 주문 처리 후 영수증 출력 메서드 호출
				printBill(user.getName(), String.valueOf(user.getPhone()), address);
			} else {
				System.out.print("배송받을 고객명을 입력하세요 ");
				String name = input.nextLine();
				System.out.print("배송받을 고객의 연락처를 입력하세요 ");
				String phone = input.nextLine();
				System.out.print("배송받을 고객의 배송지를 입력해주세요 ");
				String address = input.nextLine();
				// 주문 처리 후 영수증 출력 메서드 호출
				printBill(name, phone, address);
			}
		}
	}

	public static void menuExit() {
		System.out.println("The end");
	}

	public static void bookList(ArrayList<Book> book) {
		setFileToBookList(book);
		/*
		 * book[0] = new Book("book1", "ISBN 978-89-01-26726-5", "빅 히스토리", 33000);
		 * book[0].setAuthor("데이비드 크리스천");
		 * book[0].setDescription("우주와 지구, 인간을 하나로 잇는 새로운 역사");
		 * book[0].setCategory("인문 교양"); book[0].setReleaseDate("2022/12/23"); book[1] =
		 * new Book("book2","ISBN 979-11-6921-062-1","SICP", 45000);
		 * book[1].setAuthor("해럴드 에이블슨, 류광");
		 * book[1].setDescription("컴퓨터 프로그래밍의 구조와 해석"); book[1].setCategory("개발 방법론");
		 * book[1].setReleaseDate("2022/12/30"); book[2] = new Book("book3",
		 * "ISBN 978-89-6626-366-0", "러스트 프로그래밍", 35000);
		 * book[2].setAuthor("팀 맥나마라, 장연호");
		 * book[2].setDescription("러스트는 시스템 프로그래밍에 적합한 언어");
		 * book[2].setCategory("프로그래밍 언어"); book[2].setReleaseDate("2022/07/08");
		 */
	}

	// 장바구니에 도서 추가
	public static boolean isCartInBook(String bookId) {
		return cart.isCartInBook(bookId);
	}

	// 관리자 로그인 정보 확인 메서드
	public static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요");
		Scanner input = new Scanner(System.in);
		System.out.print("아이디 : ");
		String adminId = input.next();
		System.out.print("비밀번호 : ");
		String adminPW = input.next();
		Admin admin = new Admin(user.getName(), user.getPhone());
		if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
			String[] writeBook = new String[8];
			System.out.println("도서 정보를 추가하겠습니까? Y | N ");
			String str = input.next();
			if (str.toUpperCase().equals("Y")) {
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
				String strDate = formatter.format(date);
				writeBook[0] = "book" + strDate;
				System.out.println("도서 ID : " + writeBook[0]);
				String str1 = input.nextLine();
				System.out.println("번호 부분만 입력 예) 123-12-12345-01-1");
				System.out.print("ISBN : ");
				writeBook[1] = input.nextLine();
				writeBook[1] = "ISBN " + writeBook[1];
				System.out.print("도서명 : ");
				writeBook[2] = input.nextLine();
				System.out.print("가격(숫자) : ");
				writeBook[3] = input.nextLine();
				System.out.print("저자 : ");
				writeBook[4] = input.nextLine();
				System.out.print("설명 : ");
				writeBook[5] = input.nextLine();
				System.out.print("분야 : ");
				writeBook[6] = input.nextLine();
				System.out.print("출판일(예:2023/01/01): ");
				writeBook[7] = input.nextLine();
				try {
					// 새 도서 정보를 파일에 추가하기 위해 생성자에 true 옵션 설정
					FileWriter fw = new FileWriter("res/book.txt",true);
					 
					for (int i = 0; i < 8; i++) {
						fw.write(writeBook[i] + "\n");
					}

					fw.close();

					System.out.println("새 도서 정보가 저장되었습니다.");
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				System.out.println("이름 : " + admin.getName() + ", 연락처 : " + admin.getPhone());
				System.out.println("아이디 : " + admin.getId() + ", 비밀번호 : " + admin.getPassword());
			}
		} else {
			System.out.println("관리자 정보가 일치하지 않습니다.");
		}
	}

	// 주문 영수증 출력 메서드
	public static void printBill(String name, String phone, String address) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		System.out.println();
		System.out.println("---------------배송 받을 고객 정보----------------");
		System.out.println("고객명 : " + name + " \t\t 연락처 : " + phone);
		System.out.println("배송지 : " + address + "\t 발송일 : " + strDate);
		// 장바구니에 담긴 항목 출력
		cart.printCart();
		// 장바구니에 담긴 항목의 총 금액 계산
		int sum = 0;
		for (int i = 0; i < cart.cartCount; i++) {
			sum += cart.cartItem.get(i).getTotalPrice();
		}
		System.out.println("\t\t 주문 총금액 : " + sum + "원\n");
		System.out.println("----------------------------------------------");
		System.out.println();
	}

	// 도서 정보 목록을 파일에서 읽어 도서의 개수를 알아내는 메서드
	public static int totalFileToBookList() {
		try {
			FileReader fr = new FileReader("res/book.txt");
			BufferedReader reader = new BufferedReader(fr);
			String str;
			int num = 0; // 도서의 개수
			while ((str = reader.readLine()) != null) {
				if (str.contains("ISBN")) {
					++num;
				}
			}
			reader.close();
			fr.close();
			return num;
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	// 도서의 정보를 읽고 배열에 저장하는 메서드
	public static void setFileToBookList(ArrayList<Book> bookList) {
		try {
			FileReader fr = new FileReader("res/book.txt");
			BufferedReader reader = new BufferedReader(fr);
			String bookId;
			String[] readBook = new String[8];
			int count = 0;
			while ((bookId = reader.readLine()) != null) {
				if (bookId.contains("book")) {
					readBook[0] = bookId;
					readBook[1] = reader.readLine();
					;
					readBook[2] = reader.readLine();
					readBook[3] = reader.readLine();
					readBook[4] = reader.readLine();
					readBook[5] = reader.readLine();
					readBook[6] = reader.readLine();
					readBook[7] = reader.readLine();
					
				}
				// booklist[count++] = new Book(readBook[0], readBook[1], readBook[2],
				// Integer.parseInt(readBook[3]),
				// readBook[4], readBook[5], readBook[6], readBook[7]);
				Book book = new Book(readBook[0], readBook[1], readBook[2], Integer.parseInt(readBook[3]), readBook[4],
						readBook[5], readBook[6], readBook[7]);
				bookList.add(book);
			}
			reader.close();
			fr.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
