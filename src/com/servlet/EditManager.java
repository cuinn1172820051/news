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
 * Servlet implementation class EditManager
 */
@WebServlet(name="EditManager", urlPatterns="/EditManager")
public class EditManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditManager() {
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
		String manager_name=request.getParameter("manager_name");
		Manager manager = new Manager();
		manager.setManager_id(manager_id);
		manager.setManager_name(MyFuns.convert2utf8(manager_name));
		if(manager.getManager_id()==null || "".equals(manager.getManager_id())
				||manager.getManager_name()==null || "".equals(manager.getManager_name())
			) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.print("<HTML>");
			out.print("<HEAD><TITLE></TITLE></HEAD>");
			out.print("<BODY>");
			out.print("请把信息完整输入。<a href=\"/admin/ManagerList.jsp\">返回重新输入</a>");	
			out.print("</BODY>");
			out.print("</HTML>");
			out.flush();
			out.close();
			}else {
				SqlHepler sqlHepler = new SqlHepler();
				boolean flag = sqlHepler.updateManagerName(manager);
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				if(flag) {
					response.sendRedirect("/news/admin/ManagerList.jsp");
				}else {
					out.print("<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.print("<HTML>");
					out.print("<HEAD><TITLE></TITLE></HEAD>");
					out.print("<BODY>");
					out.print("修改失败");
					out.print("<a href=\"/news/admin/ManagerList.jsp\">返回管理员列表</a>");	
					out.print("</BODY>");
					out.print("</HTML>");
					out.flush();
					out.close();
				}
				sqlHepler.destroy();
			}
		
	}

}
