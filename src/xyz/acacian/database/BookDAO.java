package xyz.acacian.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends JdbcDAO{
	private static BookDAO instance;
	
	static {
		instance = new BookDAO();
	}
	
	public static BookDAO getDAO() {
		return instance;
	}
	
	private BookDAO() {	
	}
	
	public int insertBook(BookDTO book) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql = "insert into bookmanager values (BOOK_NUM.NEXTVAL, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, book.getNum()); //시퀀스로 대체
			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getCategory());
			
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertBook() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public int updateBook(BookDTO book) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql 
			= "update bookmanager set name=?, author=?, publisher=?, gategory=? where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book.getName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getCategory());
			pstmt.setInt(5, book.getNum());		//찾아서 교체하는것!
			
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateBook() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public int deleteBook(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql = "delete from bookmanager where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteBook() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public List<BookDTO> selectAllBookList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookDTO> bookList = new ArrayList<BookDTO>();
		try {
			con = getConnection();
			
			String sql = "select * from bookmanager order by num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookDTO book = new BookDTO();
				book.setNum(rs.getInt("num"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setCategory(rs.getString("category"));
				bookList.add(book);
			}
			
		} catch(SQLException e) {
			System.out.println("[에러]selectAllBookList() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return bookList;
	}
	
	
	

}
