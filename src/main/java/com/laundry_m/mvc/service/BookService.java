package com.laundry_m.mvc.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.laundry_m.mvc.exception.InsufficientBalanceException;
import com.laundry_m.mvc.exception.InvalidUserException;
import com.laundry_m.mvc.exception.NotExistException;
import com.laundry_m.mvc.exception.NotFilledInException;
import com.laundry_m.mvc.exception.NotLoginException;
import com.laundry_m.mvc.vo.Book;

public interface BookService {
	/**
	 * 예약 등록
	 * 1. 예약을 인서트한다.
	 * 2. 예약 상세 리스트를 인서트한다.
	 * 3. 만일 메타페이를 사용한다면 메타페이 거래 내역을 추가한다.
	 * 
	 * @param: Book book(점포 아이디, 회원 아이디, 옷 수량, 예약 참고 사항(선택), 결제 방법, 총 가격, 예약 상세 리스트(옷 아이디, 천 아이디, 가격))
	 * @exception: NotLoginException(로그인하지 않고 예약을 시도할 경우 오류)
	 * 			   NotExistException(점포 아이디나 회원 아이디 등이 DB에 존재하지 않을 경우 오류)
	 * 			   InsufficientBalanceException(메타페이 사용 시 잔액이 거래 금액보다 클 경우 경우 오류)
	 * 			   NotFilledInException(필요한 필드가 입력되지 않았을 경우 오류)
	 * */
	void makeBook(Book book) throws SQLException, NotLoginException, NotExistException, InsufficientBalanceException, NotFilledInException;
	
	/**
	 * 예약 상태 갱신
	 * @param: Book book(예약 번호, 예약 상태 번호)
	 * @exception: NotLoginException(로그인하지 않고 변경을 시도할 경우 오류)
	 * 			   NotExistException(예약이 DB에 존재하지 않을 경우 오류)
	 * 			   InvalidUserException(해당 점포를 소유하지 않은 회원이 변경을 시도할 경우 오류)
	 * 			   NotFilledInException(필요한 필드가 입력되지 않았을 경우 오류)
	 * */
	void updateBookState(Book book) throws SQLException, NotLoginException, NotExistException, InvalidUserException, NotFilledInException;
	
	/**
	 * 예약 완료
	 * 1. 예약 상태를 업데이트한다.
	 * 2. 해당 예약을 정산 테이블에 인서트한다.
	 * 
	 * @param: Long bookId
	 * @exception: NotLoginException(로그인하지 않고 변경을 시도할 경우 오류)
	 * 			   NotExistException(예약이 DB에 존재하지 않을 경우 오류)
	 * 			   InvalidUserException(해당 예약을 소유하지 않은 회원이 변경을 시도할 경우 오류)
	 * */
	void updateBookComplete(Long bookId) throws SQLException, NotLoginException, NotExistException, InvalidUserException;
	
	/**
	 * 예약 취소
	 * @param: Long bookId
	 * @exception: NotLoginException(로그인하지 않고 변경을 시도할 경우 오류)
	 * 			   NotExistException(예약이 DB에 존재하지 않을 경우 오류)
	 * 			   InvalidUserException(해당 예약이나 세탁소를 소유하지 않은 회원이 변경을 시도할 경우 오류)
	 * */
	void updateBookCanceled(Long bookId) throws SQLException, NotLoginException, NotExistException, InvalidUserException;
	
	/**
	 * 전체 예약 검색
	 * @return: List<Book>
	 * @exception: NotLoginException(로그인하지 않고 검색을 시도할 경우 오류)
	 * 			   InvalidUserException(관리자가 아닐 경우 오류)
	 * */
	List<Book> searchBookAll() throws SQLException, NotLoginException, InvalidUserException;
	
	/**
	 * 날짜로 예약 검색
	 * @param: String date
	 * @return: List<Book>
	 * */
	public List<Book> searchBookByDate(String date) throws SQLException;
	
	/**
	 * 유저 아이디로 예약 검색
	 * @param: Map<String, Object> map(유저 아이디, 예약 상태 번호(선택 - 없을 경우 모든 예약 상태 검색, 있을 경우 해당 예약 상태 번호만 검색))
	 * @return: List<Book>
	 * @exception: NotLoginException(로그인하지 않고 검색을 시도할 경우 오류)
	 * 			   NotExistException(회원이 DB에 존재하지 않을 경우 오류)
	 * 			   InvalidUserException(관리자나 해당 회원이 아닐 경우 오류)
	 * 			   NotFilledInException(필요한 필드가 입력되지 않았을 경우 오류)
	 * */
	List<Book> searchBookByUserId(Map<String, Object> map) throws SQLException, NotLoginException, NotExistException, InvalidUserException;
	
	/**
	 * 점포 아이디로 예약 검색
	 * @param: Map<String, Object> map(점포 아이디, 예약 상태 번호(선택 - 없을 경우 모든 예약 상태 검색, 있을 경우 해당 예약 상태 번호만 검색))
	 * @return: List<Book>
	 * @exception: NotLoginException(로그인하지 않고 검색을 시도할 경우 오류)
	 * 			   NotExistException(점포가 DB에 존재하지 않을 경우 오류)
	 * 			   InvalidUserException(관리자가 아니거나 해당 점포를 소유하지 않을 경우 오류)
	 * 			   NotFilledInException(필요한 필드가 입력되지 않았을 경우 오류)
	 * */
	List<Book> searchBookByLaundryId(Map<String, Object> map) throws SQLException, NotLoginException, NotExistException, InvalidUserException;
	
	/**
	 * 유저 아이디로 예약 검색
	 * @param: Long bookId
	 * @return: Book
	 * @exception: NotLoginException(로그인하지 않고 검색을 시도할 경우 오류)
	 * */
	Book existBookByBookState(Book book)throws SQLException, NotLoginException;
}