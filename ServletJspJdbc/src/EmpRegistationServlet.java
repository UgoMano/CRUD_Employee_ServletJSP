import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBConnection;

/**
 * Servlet implementation class JDBCServlet
 */
//@WebServlet("/EmpRegistrationServlet")
public class EmpRegistationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpRegistationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();

		try {
			
			String firstName, lastName, middleName, phoneNumber, emailid, address;

			firstName = request.getParameter("firstName");
			lastName = request.getParameter("lastName");
			middleName = request.getParameter("middleName");
			phoneNumber = request.getParameter("phoneNumber");
			emailid = request.getParameter("emailId");
			address = request.getParameter("address");

//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training_db","root", "admin");
			
			Connection con=DBConnection.getConnection();
			PreparedStatement st = con.prepareStatement("INSERT INTO EMPLOYEE (ID,FIRSTNAME,LASTNAME,MIDDLENAME,PHONENUMBER,EMAILID,ADDRESS) VALUES (?,?,?,?,?,?,?)");
			st.setInt(1, Integer.parseInt(request.getParameter("id")));
			st.setString(2, firstName);
			st.setString(3, lastName);
			st.setString(4, middleName);
			st.setString(5, phoneNumber);
			st.setString(6, emailid);
			st.setString(7, address);

			boolean rs = st.execute();
			if (!rs) {
				System.out.println("Record "+request.getParameter("id")+" is inserted successfully.");
			} else {
				System.out.println("Record is inseration is failed.");
			}
			st.close();
			//con.close();
			
			RequestDispatcher rd=request.getRequestDispatcher("/DisplayEmpListServlet");
			rd.forward(request, response);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			pw.println("Record is inseration is failed.");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}
