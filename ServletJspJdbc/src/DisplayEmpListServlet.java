

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.EmployeeBean;
import com.dbcon.DBConnection;

/**
 * Servlet implementation class DisplayServlet
 */
public class DisplayEmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayEmpListServlet() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<EmployeeBean> empList = new ArrayList<EmployeeBean>();
		try {

//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training_db",	"root", "admin");
			Connection con=DBConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE");
			while (rs.next()) {
				EmployeeBean bean = new EmployeeBean();

				bean.setId(rs.getInt(1));
				bean.setFirstname(rs.getString(2));
				bean.setLastname(rs.getString(3));
				bean.setMiddlename(rs.getString(4));
				bean.setPhonenumber(rs.getString(5));
				bean.setEmail(rs.getString(6));
				bean.setAddress(rs.getString(7));

				empList.add(bean);
			}
			rs.close();
			st.close();
			con.close();
			request.setAttribute("empList", empList);
			RequestDispatcher rd=request.getRequestDispatcher("/DisplayEmployeeList.jsp");
			rd.forward(request, response);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
