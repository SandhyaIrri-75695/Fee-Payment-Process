import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=false;
		PrintWriter pw =response.getWriter();
		
		   int id = Integer.parseInt(request.getParameter("id"));
		   float amount =Float.parseFloat(request.getParameter("fees"));
		   
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/region","root","sandhya@123");
			String q = "update Payment SET Amount = ?  where id = ?";
			PreparedStatement psmt=conn.prepareStatement(q);
			psmt.setFloat(1,amount);
			psmt.setInt(2, id);
			int k = psmt.executeUpdate();
			if(k>0) {
				flag =true;
		}
		}catch(Exception e) {
			pw.print(e);
		}
		if(flag==true) {
			pw.println("<html <head></head>");
	        pw.println("<body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
			pw.println("<h1 style='color:green'> Your Fees Sucessfully Updated!</h1><br>");
			pw.println("<a href='servlet'> <h2>Student fees Details</h2></a><br>");
			pw.println("<a href = 'main.html'> <h2>Home Page!</h2> </a>");
			pw.println("</body></html>");
			
	}

}
}