import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    boolean flag= false;
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
		int id =Integer.parseInt(request.getParameter("id"));
		String pswd = request.getParameter("pswd");
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/region","root","sandhya@123");
		 Statement stmt =conn.createStatement();
		 String q = "select * from Management";
		 ResultSet res = stmt.executeQuery(q);
		 while(res.next()) {
			 if(res.getInt(1)==id && res.getString(2).equals(pswd)) {
				 flag = true;
				 break;
		  }
		}
		}catch(Exception e) {
			 pw.println(e);
		 }
		 if(flag==true) {
			 pw.println("<html><body style='padding-top:150px;'>");
			 
		     
			 pw.println("<a href = 'servlet'><center><img src='Images/p.jpg'></center></a>");
			 pw.println("<center><h3 style='color:blue'>Student Details</h3></center>");
			 pw.println("</body></html>");
		 }
		 else {
			 pw.println("<h1 style='color:red'>Invalid login</h1>");
			 
		 }
		 
			 
		 }
		 
		 
		
	}

