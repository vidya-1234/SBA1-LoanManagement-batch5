package main.java.com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mysql.cj.xdevapi.Statement;

import main.java.com.iiht.evaluation.eloan.dao.ConnectionDao;
import main.java.com.iiht.evaluation.eloan.model.LoanInfo;




@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbc:mysql://localhost:3306/loanmanagement");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "validate":
				viewName=validate(request,response);
				break;
			case "placeloan":
				viewName=placeloan(request,response);
				break;
			case "application1":
				viewName=application1(request,response);
				break;
			case "editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "registeruser":
				viewName=registerUser(request,response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;	
			case  "displaystatus" :
				viewName=displaystatus(request,response);
				break;
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	}
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to validate the user */
		
		return null;
	}
	private String placeloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to place the loan information */
		
		return null;
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */
		
		return null;
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		
		String applno = request.getParameter("applno");
   	 	String purpose = request.getParameter("purpose");
   	 	int amtrequest = Integer.parseInt(request.getParameter("amtrequest"));
   	 	String doa = request.getParameter("doa");
   	 	String bstructure = request.getParameter("bstructure");
   	 	String bindicator = request.getParameter("bindicator");
   	 	String address = request.getParameter("address");
   	 	String email = request.getParameter("email");
   	 	String mobile = request.getParameter("mobile");
   	 	String status = request.getParameter("status");

        LoanInfo loan = new LoanInfo(applno, purpose, amtrequest, doa, bstructure, bindicator,
    			address, email, mobile, status);
        ConnectionDao.updateLoan(loan);
        response.sendRedirect("list");
		return null;
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
		
        return "register.jsp";
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		/* write the code to create the new user account read from user 
		   and return to index page */
		RequestDispatcher dispatcher = request.getRequestDispatcher("newuserui.jsp");
        dispatcher.forward(request, response);
		return "newuserui.jsp";
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
		return "register.jsp";
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		
		return null;
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/* write a code to return to editloan page */
		RequestDispatcher dispatcher = request.getRequestDispatcher("editloan.jsp");
        dispatcher.forward(request, response);
		return "editloan.jsp";
		
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		RequestDispatcher dispatcher = request.getRequestDispatcher("trackloan.jsp");
        dispatcher.forward(request, response);
		return "trackloan.jsp";
	}

	private String application(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		RequestDispatcher dispatcher = request.getRequestDispatcher("application.jsp");
        dispatcher.forward(request, response);
		return "application.jsp";
	}
}