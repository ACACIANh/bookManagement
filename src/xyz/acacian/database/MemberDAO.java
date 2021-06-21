package xyz.acacian.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.acacian.enums.EMemberAttribute;

public class MemberDAO extends JdbcDAO {
	private static MemberDAO instance;
	
	static {
		instance = new MemberDAO();
	}
	
	public static MemberDAO getDAO() {
		return instance;
	}
	
	private MemberDAO() {	
	}
	
	public int insertMember(MemberDTO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql = 
					"insert into membermanager values (MEMBER_NUM.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, member.getNum()); //시퀀스로 대체
			pstmt.setInt(1, member.getId_level());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPw());
			pstmt.setString(4, member.getName());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getBirthday());
			
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertMember() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public int updateMember(MemberDTO member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql 
			= "update membermanager set id_level=?, id=?, pw=?, name=?, phone=?, birthday=? where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member.getId_level());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPw());
			pstmt.setString(4, member.getName());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getBirthday());
			pstmt.setInt(7, member.getNum());		//찾아서 교체하는것!
			
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateMember() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public int deleteMember(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql = "delete from membermanager where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteMember() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public List<MemberDTO> selectAllMemberList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			con = getConnection();
			
			String sql = "select * from membermanager order by num";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setNum(rs.getInt("num"));
				member.setId_level(rs.getInt("id_level"));
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setBirthday(rs.getString("birthday"));
				memberList.add(member);
			}
			
		} catch(SQLException e) {
			System.out.println("[에러]selectAllMemberList() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return memberList;
	}
	
	public List<MemberDTO> selectMemberList(String data, EMemberAttribute type){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			con = getConnection();
			
			String sql = "select * from membermanager where ";
			sql += type.getStringSQL();		// "name"
			sql += " like '%'||?||'%' order by num";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, data);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setNum(rs.getInt("num"));
				member.setId_level(rs.getInt("id_level"));
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setBirthday(rs.getString("birthday"));
				memberList.add(member);
			}
		} catch(SQLException e) {
			System.out.println("[에러]selectMemberList() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return memberList;
	}
	
	
	public int getLatestNum(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int latestNum = 0;
		
		try {
			con = getConnection();
			
			String sql = "select num from membermanager where rownum = 1 order by num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				latestNum = rs.getInt("num");
			}
			
		} catch(SQLException e) {
			System.out.println("[에러]getLatestNum() 메소드 SQL 오류 " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return latestNum;
	}
}
