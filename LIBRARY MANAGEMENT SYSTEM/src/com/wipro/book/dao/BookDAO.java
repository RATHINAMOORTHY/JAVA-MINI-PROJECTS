package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.bean.BookBean;
import com.wipro.book.util.DBUtil;

public class BookDAO {
	public BookBean fetchBook(String isbn)
	{
		AuthorDAO ad=new AuthorDAO();
		BookBean ab=new BookBean();
		Connection con=DBUtil.getDBConnection();
		try 
		{
			PreparedStatement ps=con.prepareStatement("select * from Book_tbl where ISBN=?");
			ps.setString(1, isbn);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ab.setIsbn(rs.getString(1));
				ab.setBookName(rs.getString(2));
				ab.setBookType(rs.getString(3).charAt(0));
				ab.setAuthor(ad.getAuthor(rs.getInt(4)));
				ab.setCost(rs.getFloat(5));	
			} 
			return ab;
		} catch (SQLException e)
		{
			return null;
		}	
	}
	public int createBook(BookBean bookBean)
	{
		int a=0;
		Connection con=DBUtil.getDBConnection();
		try {
			PreparedStatement ps=con.prepareStatement("insert into Book_Tbl values(?,?,?,?,?)");
			ps.setString(1, bookBean.getIsbn());
			ps.setString(2, bookBean.getBookName());
			ps.setString(3,String.valueOf(bookBean.getBookType()));
			ps.setInt(4, bookBean.getAuthor().getAuthorCode());
			ps.setFloat(5,bookBean.getCost());
			a=ps.executeUpdate();
			if(a>0)
			{
				a=1;
			}
		} catch (SQLException e) {
			a=0;
		}
		return a;

	}

}
