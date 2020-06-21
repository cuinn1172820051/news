package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Manager;
import com.sql.SqlHepler;

/**
 * Servlet implementation class DeleteManager
 */
@WebServlet(name="DeleteManager", urlPatterns="/DeleteManager")
public class DeleteManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String manager_id = request.getParameter("manager_id");
		if(!(manager_id==null || "".equals(manager_id))) {
			Manager manager = new Manager();
			manager.setManager_id(manager_id);
			SqlHepler sqlHepler = new SqlHepler();
			sqlHepler.deleteManager(manager);
			sqlHepler.destroy();
		}
		response.sendRedirect("admin/ManagerList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
