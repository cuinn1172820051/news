package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Manager;
import com.sql.SqlHepler;
import com.tools.MyFuns;

/**
 * Servlet implementation class InsertManager
 */
@WebServlet(name="InsertManager", urlPatterns="/InsertManager")
public class InsertManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertManager() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String manager_id = request.getParameter("manager_id");
		String manager_name = request.getParameter("manager_name");
		String manager_pwd = request.getParameter("manager_pwd");
		
		Manager manager = new Manager();
		manager.setManager_id(MyFuns.convert2utf8(manager_id));
		manager.setManager_name(MyFuns.convert2utf8(manager_name));
		manager.setManager_pwd(manager_pwd);
		if(manager.getManager_id()==null || "".equals(manager.getManager_id())
			||manager.getManager_name()==null || "".equals(manager.getManager_name())
			||manager.getManager_pwd()==null || "".equals(manager.getManager_pwd())
		) {
			out.print("<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.print("<HTML>");
			out.print("<HEAD><TITLE></TITLE></HEAD>");
			out.print("<BODY>");
			out.print("请把信息完整输入。<a href=\"/admin/addManager.jsp\">返回重新输入</a>");	
			out.print("</BODY>");
			out.print("</HTML>");
			out.flush();
			out.close();
		}else {
			SqlHepler sqlHepler = new SqlHepler();
			if(sqlHepler.queryManagerById(manager.getManager_id())!=null) {
				out.print("<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.print("<HTML>");
				out.print("<HEAD><TITLE></TITLE></HEAD>");
				out.print("<BODY>");
				out.print("用户名已存在。<a href=\"/news/admin/addManager.jsp\">返回重新输入</a>");	
				out.print("</BODY>");
				out.print("</HTML>");
				out.flush();
				out.close();
				sqlHepler.destroy();
			}else {
				boolean b = sqlHepler.insertManager(manager);
				response.sendRedirect("/news/admin/ManagerList.jsp");
				sqlHepler.destroy();
			}	
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
