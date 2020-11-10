package main.java.com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import main.java.com.iiht.evaluation.eloan.model.LoanInfo;
import main.java.com.iiht.evaluation.eloan.model.User;



public class ConnectionDao {
	private static final long serialVersionUID = 1L;
	private static String jdbcURL;
	private static String jdbcUsername;
	private static String jdbcPassword;
	private static Connection jdbcConnection;

	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	public static  Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	
	// put the relevant DAO methods here..
	
	 		private static final String INSERT_LOAN_SQL = "INSERT INTO LoanApp" + "  (purpose, amtrequest, doa, bstructure, bindicator, address, status, email, mobile) VALUES " +
		        " (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	 		private static final String SELECT_ALL_USER = "select * from User";
		    private static final String SELECT_ALL_LOAN = "select * from LoanApp";
		    private static final String SELECT_LOAN_ID = "select * from LoanApp where applno =?";
		    private static final String SELECT_LOANDetails = "select * from ApprovedLoan where applnum =?";
		    private static final String SELECT_LOANSTATUS_ID = "select l.status, l.applno from LoanApp l where applnum=?";
		   	private static final String UPDATE_LOAN_SQL = "update LoanApp set purpose = ?,amtrequest= ?, doa =?, bstructure=?, bindicator=?, address=?, status=?, email=?, mobile=?  where applno = ?;";

			public static String getInsertLoanSql() {
				return INSERT_LOAN_SQL;
			}

			public static String getSelectAllLoan() {
				return SELECT_ALL_LOAN;
			}

			public static String getSelectLoanId() {
				return SELECT_LOAN_ID;
			}

			public static String getSelectLoanstatusId() {
				return SELECT_LOANSTATUS_ID;
			}

			public static String getUpdateLoanSql() {
				return UPDATE_LOAN_SQL;
			}
			
			public static String getSelectLoandetails() {
					return SELECT_LOANDetails;
				}
						
			public static String getSelectAllUser() {
				return SELECT_ALL_USER;
			}


			
	public static List<User> selectAllUser() {
				
		List <User> user = new ArrayList < > ();
				try (
				
				Connection connection = connect();
			    PreparedStatement preparedStatement = connection.prepareStatement(getSelectAllUser());) { 
			    System.out.println(preparedStatement);
			    
			    ResultSet rs = preparedStatement.executeQuery();

			       while (rs.next()) {
			        
			    	String username = rs.getString("username");
			   	 	String password = rs.getString("password");
			   	  user.add(new User(username, password));
			    }
			} catch (SQLException e) {
				printSQLException(e);
			}
			return null;

				
			}
			
			
			
public static boolean updateLoan(LoanInfo loan) throws SQLException {
	boolean rowUpdated;
    try (
    	
    	
    	Connection connection = connect(); 
    		
    	PreparedStatement statement = connection.prepareStatement(getUpdateLoanSql());) {
    	statement.setString(1, loan.getStatus());
    	
        rowUpdated = statement.executeUpdate() > 0;
        System.out.println("Added succesfully");
        
    }
    
    return rowUpdated;
      
}
			
			
			
			
public static List<LoanInfo> selectAllLoan() {
	
	List <LoanInfo> loan = new ArrayList < > ();
	try (
	
	Connection connection = connect();
    PreparedStatement preparedStatement = connection.prepareStatement(getSelectAllLoan());) { 
    System.out.println(preparedStatement);
    
    ResultSet rs = preparedStatement.executeQuery();

       while (rs.next()) {
        
    	String applno = rs.getString("applno");
   	 	String purpose = rs.getString("purpose");
   	 	int amtrequest = rs.getInt("amtrequest");
   	 	String doa = rs.getString("doa");
   	 	String bstructure = rs.getString("bstructure");
   	 	String bindicator = rs.getString("bindicator");
   	 	String address = rs.getString("address");
   	 	String email = rs.getString("email");
   	 	String mobile = rs.getString("mobile");
   	 	String status = rs.getString("status");
        loan.add(new LoanInfo(applno, purpose, amtrequest, doa, bstructure, bindicator,
    			address, email, mobile, status));
    }
} catch (SQLException e) {
	printSQLException(e);
}
return null;

	
}


public static LoanInfo selectLoan(int appno) {
    LoanInfo loan = null;
    
    try (Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(getSelectLoanId());) {
        preparedStatement.setInt(1, appno);
        System.out.println(preparedStatement);
      
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
        	String applno = rs.getString("applno");
       	 	String purpose = rs.getString("purpose");
       	 	int amtrequest = rs.getInt("amtrequest");
       	 	String doa = rs.getString("doa");
       	 	String bstructure = rs.getString("bstructure");
       	 	String bindicator = rs.getString("bindicator");
       	 	String address = rs.getString("address");
       	 	String email = rs.getString("email");
       	 	String mobile = rs.getString("mobile");
       	 	String status = rs.getString("status");
            loan = new LoanInfo(applno, purpose, amtrequest, doa, bstructure, bindicator,
        			address, email, mobile, status);
        }
    } catch (SQLException e) {
    	printSQLException(e);
    }
    return loan;
}


public static LoanInfo selectLoanStatus(int appno) {
	 LoanInfo loan = null;
	    
	    try (Connection connection = connect();
	        PreparedStatement preparedStatement = connection.prepareStatement(getSelectLoanstatusId());) {
	        preparedStatement.setInt(1, appno);
	        System.out.println(preparedStatement);
	      
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	        	String applno = rs.getString("applno");
	       	 	String purpose = rs.getString("purpose");
	       	 	int amtrequest = rs.getInt("amtrequest");
	       	 	String doa = rs.getString("doa");
	       	 	String bstructure = rs.getString("bstructure");
	       	 	String bindicator = rs.getString("bindicator");
	       	 	String address = rs.getString("address");
	       	 	String email = rs.getString("email");
	       	 	String mobile = rs.getString("mobile");
	       	 	String status = rs.getString("status");
	            loan = new LoanInfo(applno, purpose, amtrequest, doa, bstructure, bindicator,
	        			address, email, mobile, status);
	        }
	    } catch (SQLException e) {
	    	printSQLException(e);
	    }
	    return loan;
	
	
}


public static void insertLoan(LoanInfo loan) throws SQLException {
    System.out.println(getInsertLoanSql());
    try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(getInsertLoanSql());) {
        preparedStatement.setString(1, loan.getApplno());
        preparedStatement.setString(2, loan.getPurpose());
        preparedStatement.setInt(3, loan.getAmtrequest());
        preparedStatement.setString(4, loan.getDoa());
        preparedStatement.setString(5, loan.getBstructure());
        preparedStatement.setString(6, loan.getBindicator());
        preparedStatement.setString(7, loan.getAddress());
        preparedStatement.setString(8, loan.getEmail());
        preparedStatement.setString(9, loan.getMobile());
        preparedStatement.setString(10, loan.getStatus());
        
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
    	printSQLException(e);
    }
}


private static void printSQLException(SQLException ex) {
    for (Throwable e: ex) {
        if (e instanceof SQLException) {
            e.printStackTrace(System.err);
            System.err.println("SQLState: " + ((SQLException) e).getSQLState());
            System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
            System.err.println("Message: " + e.getMessage());
            Throwable t = ex.getCause();
            while (t != null) {
                System.out.println("Cause: " + t);
                t = t.getCause();
            }

        }
    }
    
}
        }
