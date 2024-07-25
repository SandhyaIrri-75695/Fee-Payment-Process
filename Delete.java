import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw =response.getWriter();
	
	        int id =Integer.parseInt(request.getParameter("id"));
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/region","root","sandhya@123");
			String q = "Delete from Payment where id= ?";
			PreparedStatement stmt=conn.prepareStatement(q);
			stmt.setInt(1,id);
		    int k = stmt.executeUpdate();
		    if(k>0) {
		    	pw.println("<html <head></head>");
		        pw.println("<body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
		       pw.println("<h1 style='color:red'> Deleted successfully</h1><br>");
		       pw.println("<a href='servlet'><h2> Student fees Details</h2></a><br><br>");
		       pw.println("<a href = 'main.html'><h2>Home Page! </h2></a>");
		       pw.println("</html>");
		    }else {
		    	pw.println("<h1 style='color:red'> Invalid! </h1><br>");
		    	pw.println("<a href='servlet'><h2>Student fees Details</h2></a><br><br>");
		    	pw.println("<a href = 'main.html'><h2> Home Page! </h2> </a>");
		    }
		}catch(Exception e) {
			pw.print(e);
		}
					
			
			
	}

}
