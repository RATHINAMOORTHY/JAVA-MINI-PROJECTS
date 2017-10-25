package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DBUtil;

public class AuthorDAO {
	public AuthorBean getAuthor(int authorCode)
	{
		AuthorBean ab=new AuthorBean();
		Connection con=DBUtil.getDBConnection();
		try 
		{
			PreparedStatement ps=con.prepareStatement("select * from Author_tbl where author_Code=?");
			ps.setInt(1, authorCode);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ab.setAuthorCode(rs.getInt(1));
				ab.setAuthorName(rs.getString(2));
				ab.setContactNo(rs.getLong(3));
			}
			return ab;
		} catch (SQLException e)
		{
			
			return null;
		}

	}
	public AuthorBean getAuthor(String authorName)
	{

		AuthorBean ab=new AuthorBean();
		Connection con=DBUtil.getDBConnection();
		try 
		{
			PreparedStatement ps=con.prepareStatement("select * from Author_tbl where author_Name=?");
			ps.setString(1,authorName);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ab.setAuthorCode(rs.getInt(1));
				ab.setAuthorName(rs.getString(2));
				ab.setContactNo(rs.getLong(3));
			}
			return ab;
		} catch (SQLException e)
		{
			return null;
		}

	}

}
