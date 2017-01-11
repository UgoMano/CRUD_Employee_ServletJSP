

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.EmployeeBean;
import com.dbcon.DBConnection;

/**
 * Servlet implementation class UpdateEmpServlet
 */

//@WebServlet("/UpdateEmpServlet")
public class UpdateEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmpServlet() {
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
		// TODO Auto-generated method stub
		

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		if (request.getParameter("update") != null)
			updateInfo(request, response);
		else if (request.getParameter("delete") != null)
			deleteInfo(request, response);
		
	}
	
	private void updateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<EmployeeBean> empList = new ArrayList<EmployeeBean>();
		
		String id = request.getParameter("radio");
		System.out.println("UPDATE");
		System.out.println("***********************");
		System.out.println("The value is "+ id);
		
		
		int idInt = Integer.parseInt(id);
		
		EmployeeBean updateBean = null;
		//request.setAttribute("idVal", Integer.parseInt(id));
		
		try {
			Connection con=DBConnection.getConnection();
			
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE");
		
			System.out.println("The value AGAIN is "+ id);
		
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
			
			for (EmployeeBean bean: empList){
		        if(bean.getId() == idInt){
		        	updateBean = bean;
		        }
		        
		        
		    }
			
			System.out.println(updateBean);
			rs.close();
			st.close();
			con.close();
			
			
			request.setAttribute("updateBean", updateBean);
			System.out.println("!!!Before forward");
			request.getRequestDispatcher("/UpdateInfoForm.jsp").forward(request, response);
			System.out.println("!!!After forward");
			
		}catch (Exception e) {
			
			    
			e.printStackTrace();
		}
		
		
		
//		PrintWriter out = response.getWriter();
//		out.println("<h1>BLESSINGS</h1>");
	}
	
	private void deleteInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<EmployeeBean> empList = new ArrayList<EmployeeBean>();
		
		String id = request.getParameter("radio");
		System.out.println("DELETE");
		System.out.println("***********************");
		System.out.println("The value is "+ id);
		
		
		int idInt = Integer.parseInt(id);
		
		try {
			Connection con=DBConnection.getConnection();
			
			
			String deleteSQL = "DELETE FROM EMPLOYEE WHERE ID = ?";
			PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idInt);
			
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE");
		
			System.out.println("The value AGAIN is "+ id);
		
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
			preparedStatement.close();
			con.close();
			
			request.setAttribute("empList", empList);
			System.out.println("!!!Before forward");
			request.getRequestDispatcher("/DisplayEmployeeList.jsp").forward(request, response);
			System.out.println("!!!After forward");
			
		}catch (Exception e) {
			
			    
			e.printStackTrace();
		}
		
	}

}
