import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class fees
 */
@WebServlet("/fees")
public class fees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int from = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @param select 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=false;
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
		int id1 =Integer.parseInt(request.getParameter("id1"));
		String pswd = request.getParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/region","root","sandhya@123");
		    Statement stmt = conn.createStatement();
		    String q ="select * from first";
		    ResultSet res = stmt.executeQuery(q);
		    while(res.next()) {
		    	if(res.getInt(1)== id1 && res.getString(2).equals(pswd)) {
		    		flag=true;
		    		break;
		    	}
		    }
		}
		
		 catch(Exception e) {
			 pw.println(e);
		 }
		if(flag==true) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/region","root","sandhya@123");
				String q = "select * from first where id = ?";
				PreparedStatement pst =conn.prepareStatement(q);
				pst.setInt(1,id1);
				ResultSet re = pst.executeQuery();
				pw.println("<html <head></head>");
		        pw.println("<body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
				pw.println("<center><fieldset style='height:300px; width:350px;'><form action = 'first'>");
				pw.println("<h1> student details</h1>");
				while(re.next()) {
					pw.println("student-ID:<input type = 'text' name='id' value="+re.getInt(1)+"><br><br>");
					pw.println("Student-name:<input type='text' name='name' value="+re.getString(3)+"><br><br>");
					pw.println("Gender:<input type='text' name='gender' value="+re.getString(4)+"><br><br>");
					pw.println("Amount:<input type='text' name='fees' value='value'><br><br>");
					pw.println("<input type='checkbox'required> <b>I Agree to pay</b><br><br>");
					pw.println("<button type='submit' value='B' name='action'> Back</button>&nbsp;&nbsp;");
					pw.println("<button type='submit' value='P' name='action'>Pay</button>&nbsp;&nbsp;");
					pw.println("<input type='Reset'>");
					
				}
			   pw.println("</center></fieldset></form></body></html>");
			}
		    catch(Exception e) {
		    	pw.println(e);
		    }

		}else {
			pw.println("<h1 style='color:red'>InvalidLogin!</h1>");
		}
		
	}

}