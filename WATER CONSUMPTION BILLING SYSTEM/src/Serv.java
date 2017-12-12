

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connect.Connect;


/**
 * Servlet implementation class Serv
 */
@WebServlet("/Serv")
public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public Serv() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.print("<html>");
		out.print("<body bgcolor='#FFE4E1'>");
		out.print("<a href='index.html'><img src='src/fet.png' height='150' width='150'><img src='src/logo.png' height='150' width='1000'><br></a>");
		out.println("<br/><h1>WATER CONSUMPTION BILLING SYSTEM</h1>");
		out.print("<a href='index.html'><img src='src/home.png' height='70' width='70'></a>");
		Connection con=Connect.getCon();
		if(request.getParameter("choose").equals("add"))
		{
			String[] st=new String[8];
			int i=0;
			Enumeration<String> e=request.getParameterNames();
			while(e.hasMoreElements())
			{
				st[i++]=request.getParameter((String)e.nextElement());		
			}
			try {
				PreparedStatement ps = con.prepareStatement("insert into water values(?,?,?,?,?,?,?)");
				for(int j=0;j<6;j++)
				{
					if(j+1==2)
					{
						ps.setString(2, st[j].toUpperCase());
					}
					else if(j+1==3)
					{
						FileInputStream fin=new FileInputStream(st[j]);
						ps.setBinaryStream(3, fin, fin.available());	
					}
					else
					{
						ps.setInt(j+1, Integer.valueOf(st[j]));
					}
				}
				ps.setInt(7,(Integer.valueOf(st[4])-Integer.valueOf(st[3])));
				ps.execute();
				out.println("<br/><h2>CONSUMER DETAILS ADDED SUCCESSFULLY!!</br><br>");
				out.println("<a href='add.html'>ADD ANOTHER CONSUMER<img src='src/user_add.png' height='100' width='100'></a>");
			} catch (Exception e1) {
				out.println("<br/><h2>USER ALREADY PRESENT!!</br>");
                out.println("<a href='update.html'>UPDATE USER:<img src='src/user_edit.png' height='100' width='100'></a>");
				e1.printStackTrace();
			}
		}
		
		if(request.getParameter("choose").equals("update"))
		{
			String[] st=new String[5];
			int i=0;
			Enumeration<String> e=request.getParameterNames();
			while(e.hasMoreElements())
			{
				st[i++]=request.getParameter((String)e.nextElement());		
			}
			try {
				Statement stmt=con.createStatement();  
				int result=stmt.executeUpdate("update water set ir="+st[1]+"er="+st[2]+"cost="+"where hnum="+st[0]); 
				out.println("<br/><h2>CONSUMER DETAILS UPDATED SUCCESSFULLY!!</br><br>");
				out.println("<a href='update.html'>UPDATE ANOTHER CONSUMER<img src='src/user_edit.png' height='100' width='100'></a>");
					
			} catch (Exception e1) {
				out.println("<br/><h2>USER NOT PRESENT. ADD USER FIRST!!</br>");
                out.println("<a href='update.html'>ADD USER:<img src='src/user_edit.png' height='100' width='100'></a>");
				e1.printStackTrace();
			}	
		}
		if(request.getParameter("choose").equals("display"))
		{
			String id=request.getParameter("hnum");
			try {
				PreparedStatement ps = con.prepareStatement("select * from water where hnum=?");
				ps.setInt(1, Integer.valueOf(id));
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					out.println("</br></br>");
						Blob b1=rs.getBlob(3);
						FileOutputStream ou=new FileOutputStream("D:\\"+rs.getString(1)+".jpg");
						byte b2[] =b1.getBytes(1,(int)b1.length());
						ou.write(b2);
						out.println("<img src='D:\\"+rs.getString(1)+".jpg' height='100' width='100'></br></br>");
					out.println("</br>---------------------------------------------------------------------------");
					out.println("</br></br>");
					out.println("HOUSE NUMBER       :"+rs.getInt(1)+"<br/>");
					out.println("HOUSE OWNER NAME   :"+rs.getString(2)+"<br/>");
					out.println("CONSUMED WATER 	:"+rs.getInt(7)+"_Litres"+"<br/>");
					out.println("AMOUNT TO PAY      :"+(rs.getInt(7)*rs.getInt(6))+"_Rupees"+"<br/><br>");
				}
				out.println("<input type='button' value='GENERATE INVOICE' onClick='window.print()'>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		out.println("</body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
