package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.Manager;
import com.sql.SqlHepler;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", urlPatterns="/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manager_id = request.getParameter("manager_id");
		String manager_pwd = request.getParameter("manager_pwd");
		if(manager_id==null||"".equals(manager_pwd)||manager_pwd==null||"".equals(manager_pwd)) {
			response.sendRedirect("login.jsp?msg=0");
			return;	
		}
		Manager manager = new Manager();
		manager.setManager_id(manager_id);
		manager.setManager_pwd(manager_pwd);
		SqlHepler sqlHepler = new SqlHepler();
		Manager m = sqlHepler.queryManagerByIdAndPwd(manager);
		if(m==null) {
			response.sendRedirect("login.jsp?msg=2");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute(manager_id,m.getManager_id());
			session.setAttribute("login_success","islogin");
			response.sendRedirect("admin/loading.jsp");
		}
			sqlHepler.destroy();	
	}

}
