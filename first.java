
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class first
 */
@WebServlet("/first")
public class first extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int from = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public first() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
		
		int id =Integer.parseInt(request.getParameter("id"));
		
		String name=request.getParameter("name");
		
		String radio =request.getParameter("gender");
		
		float fee=Float.parseFloat(request.getParameter("fees"));
		
		//int id1 =Integer.parseInt(request.getParameter("id1"));
		String str =request.getParameter("action");
		switch(str) {
		case  "B":
			 RequestDispatcher dis = request.getRequestDispatcher("main.html");
			 dis.forward(request, response);
	    break;
		case "P":
			pw.println("<html <head></head>");
	        pw.println("<body style=\"background-image: url('Images/b.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
			pw.println("<h1 style='color:green'>Thank you for your payment</h1><br>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/region","root","sandhya@123");
			//Statement stmt =conn.createStatement();
			//pw.println("statement");
			//String q="create table Payment(id int,name varchar(10),gender varchar(10),Amount float)";
			//stmt.executeUpdate(q);
			//pw.print("created");
			
		    String s = "select * from Payment";
		    Statement st = conn.createStatement();
		    ResultSet res = st.executeQuery(s);
		    int flag =0;
		    Float Amount = 0f;
		    while(res.next()) {
		    	if(res.getInt(1)== id) {
		        	flag = 1;
		            Amount = res.getFloat(4);
				  }
			    }
		    if(flag == 1) {
		       float fees = Amount +fee;
		       String q = "update Payment SET Amount = ?  where id = ?";
			   PreparedStatement psmt=conn.prepareStatement(q);
			   psmt.setFloat(1,fees);
			   psmt.setInt(2, id);
			   psmt.executeUpdate();

		      }
		   if(flag==0) {
		       PreparedStatement stmt =conn.prepareStatement("insert into  Payment values(?,?,?,?)");
			   stmt.setInt(1, id);
			   stmt.setString(2,name);
			   stmt.setString(3,radio);
			   stmt.setFloat(4, fee);
			   stmt.executeUpdate();
			
		      }
		   pw.println("<html><body><fieldset style='height:250px; width:350px;'><form>");
		   pw.println("<center><h2> Fees Reciept</h2></center>");
		   pw.println("<table><tr><td><b>Student-Id:</td><td></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+id+"</td></tr>");
		   pw.println("<tr><td><b>Student-name:</td><td></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+name+"</td></tr>");
		   pw.println("<tr><td><b>Gender:</td><td></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+radio+"</td></tr>");
		   pw.println("<tr><td><b>Hostel-fee:</td><td></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+fee+"</td></tr>");
		   pw.println("<tr><td></td><td><b>Total:</b></td><td>&nbsp;&nbsp;"+fee+"</td></tr></table>");
		   pw.println("<h3>payment in form of on-line</h3>");
		   pw.println("</form></fieldset></body></html>");
		   pw.println("<a href = main.html> <h2><b>Home page!</b></h2></a>");
	          
		   
		   
		    }
		
	catch(Exception e) {
		pw.println(e);
	}
	break;
		}
	}
}