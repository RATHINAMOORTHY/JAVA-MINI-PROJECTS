

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Conn;

/**
 * Servlet implementation class MainBank
 */
@WebServlet("/MainBank")
public class MainBank extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainBank() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		Connection con=Conn.getConn();
		if(request.getParameter("choose").equals("add"))
		{
			out.print("<html><body bgcolor='#ff6600'>");
			out.println("<img src='logo.png'><br/><br/>");
			out.println("<a href='index.html'><input type='button' value='GO HOME'></a><br/><br/>");
			int i=0;
			String[] s=new String[6];
			Enumeration<String> e=request.getParameterNames();
			while(e.hasMoreElements())
			{
				s[i]=(String)request.getParameter((String) e.nextElement());
				i++;
			}
			try {
				PreparedStatement ps=con.prepareStatement("insert into bank values(?,?,?,?,?)");
				for(int j=0;j<5;j++)
				{
					ps.setString(j+1,s[j]);
				}
				int n=ps.executeUpdate();
				if(n>0)
				{
					out.println("INSERTED SUCCESSFULLY");
				}
				else
				{
					out.println("FAILURE");
				}

			} catch (SQLException e1) {
				out.println(e1.toString());
			}
			finally{
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			out.println("</body></html>");

		}
		if(request.getParameter("choose").equals("update"))
		{
			out.print("<html><body bgcolor='#ff6600'>");
			out.println("<img src='logo.png'><br/><br/>");
			out.println("<a href='index.html'><input type='button' value='GO HOME'></a><br/><br/>");
			int i=0;
			String[] s=new String[7];
			Enumeration<String> e=request.getParameterNames();
			while(e.hasMoreElements())
			{
				s[i]=(String)request.getParameter((String) e.nextElement());
				i++;
			}
			try {
				PreparedStatement ps=con.prepareStatement("update bank SET NAME=?,GENDER=?,LOCATION=?,BALANCE=? WHERE ID=?");
				for(int j=1;j<5;j++)
				{
					ps.setString(j, s[j]);
				}
				ps.setString(5, s[0]);
				int a=ps.executeUpdate();
				if(a>0)
				out.println("UPDATED SUCCESSFULLY");
				else
				out.println("FAILURE");
			} catch (SQLException e1) {
				out.println("FAILURE");
			}
			finally{
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			out.println("</body></html>");

		}
		if(request.getParameter("choose").equals("display"))
		{
			int v=0;
			try {
				PreparedStatement ps=con.prepareStatement("select * from bank where ID=?");
				ps.setString(1,(String)request.getParameter("id"));
				ResultSet rs=ps.executeQuery();
				out.print("<html><body bgcolor='#ff6600'>");
				out.println("<img src='logo.png'><br/><br/>");
				out.println("<a href='index.html'><input type='button' value='GO HOME'></a><br/><br/>");
				while(rs.next())
				{
					v++;
					out.println("ID     :"+rs.getString(1)+"<br/>");
					out.println("NAME   :"+rs.getString(2)+"<br/>");
					out.println("GENDER :"+rs.getString(3)+"<br/>");
					out.println("BRANCH :"+rs.getString(4)+"<br/>");
					out.println("BALANCE:"+rs.getString(5)+"<br/>");
				}
				if(v==0)
				{
					out.println("USER NOT FOUND");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.println(e.toString());
			}
			finally{
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			out.println("</body></html>");
		}
	}

}
