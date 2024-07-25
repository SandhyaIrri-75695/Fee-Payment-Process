import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Uservlet
 */
@WebServlet("/Uservlet")
public class Uservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw =response.getWriter();
		
		   int id = Integer.parseInt(request.getParameter("id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/region","root","sandhya@123");
			String q = "select * from payment where id = ?";
			PreparedStatement pst =conn.prepareStatement(q);
			pst.setInt(1,id);
			ResultSet re = pst.executeQuery();
			pw.println("<html><head></head>");
			pw.println("<body style=\"background-image: url('Images/b.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
			pw.println("</body></html>");
			 pw.println("<html><body style='padding-top:50px;'><center><fieldset style='height:275px; width:350px;'><form action = 'Update'>");
			pw.println("<h1> Update the details</h1>");
			while(re.next()) {
				pw.println("<b>student-ID:</b><input type = 'text' name='id' value="+re.getInt(1)+"><br><br>");
				pw.println("<b>Student-name:</b><input type='text' name='name' value="+re.getString(2)+"><br><br>");
				pw.println("<b>Gender:</b><input type='text' name='gender' value="+re.getString(3)+"><br><br>");
				pw.println("<b>Amount:</b><input type='text' name='fees' value="+re.getFloat(4)+"><br><br>");
				
				
				pw.println("<input type ='submit' value ='save'>");
			}
			pw.println("</form></center></body></html>");
	 }
     catch(Exception e) {
    	 pw.println(e);
     }
}
}

