import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STUDENT = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw =response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/region","root","sandhya@123");
			Statement stmt=conn.createStatement();
			String q = "select * from Payment";
			ResultSet res =stmt.executeQuery(q);
			pw.println("<html><body><center><h1 style='color:blue'>STUDENT FEES DETAILS</h1></center></body></html>");
			pw.println("<html><body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">);><center><table border='5' cellpadding='20' cellspacing='2'><tr><th>Student-Id</th><th>Student-name</th><th>Gender</th><th>Amount</th><th>Update</th><th>Delete</th></tr>");
			while(res.next()) {
				int id = res.getInt(1);
				pw.println("<tr><td>"+res.getInt(1)+"</td>"+"<td>"+res.getString(2)+"</td>"+"<td>"+res.getString(3)+"</td>"+"<td>"+res.getFloat(4)+"</td>");
				//pw.println("<td><form action='Update'><input type ='hidden' name='id' value="+res.getInt(1)+"><input type='hidden' name='fees' value="+res.getFloat(4)+"><input type='submit' value='update'style='color:green' background-color='green'>></td>");
				pw.println("<td><form action='Uservlet'><input type = 'hidden' name='id' value="+res.getInt(1)+"><input type='submit' value = 'UPDATE' style='color:green' style='bgcolor:green' ></form></td>");					
				pw.println("<td><form action='delete'><input type = 'hidden' name='id' value="+res.getInt(1)+"><input type='submit' value = 'DELETE' style='color:red' style='bgcolor:red' ></form></td></tr>");
						
						
			
			}
			pw.println("</table></center></body></html><br><br>");
			pw.print("<html><body><center>");
			pw.println("<a href='main.html'> Home Page!</a> &nbsp;&nbsp;");
			//pw.print("<a href='Delete.html'><input type='submit' value='DELETE' style='color:red' style='bgcolor:red'></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			//pw.print("<a href='Update.html'><input type='submit' value='UPDATE' style='color:green' background-color='green'></a>");
			pw.print("</center></body></html>");
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
			
		}
	

	}