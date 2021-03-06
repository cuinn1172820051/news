package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Nav;
import com.sql.SqlHepler;
import com.tools.MyFuns;

import java.util.UUID;
/**
 * Servlet implementation class InsertNav
 */
@WebServlet(name="InsertNav", urlPatterns="/InsertNav")
public class InsertNav extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNav() {
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
		String nav_name = request.getParameter("nav_name");
		String nav_feight_str=request.getParameter("nav_feight");
		if(nav_name==null||nav_feight_str==null) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.print("<HTML>");
			out.print("<HEAD><TITLE></TITLE></HEAD>");
			out.print("<BODY>");
			out.print("请将信息输入完整。<a href=\"/admin/addNav.jsp\">返回重新输入</a>");	
			out.print("</BODY>");
			out.print("</HTML>");
			out.flush();
			out.close();
		}
		int nav_feight = 0;
		try {
			nav_feight = Integer.parseInt(nav_feight_str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String nav_id = UUID.randomUUID().toString();
		Nav nav = new Nav();
		nav.setNav_id(nav_id);
		nav.setNav_feight(nav_feight);
		nav.setNav_name(MyFuns.convert2utf8(nav_name));
		SqlHepler sqlhepler = new SqlHepler();
		boolean b = sqlhepler.insertNav(nav);
		sqlhepler.destroy();
		if(!b) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.print("<HTML>");
			out.print("<HEAD><TITLE></TITLE></HEAD>");
			out.print("<BODY>");
			out.print("增加类别错误。<a href=\"/admin/addNav.jsp\">返回重新输入</a>");	
			out.print("</BODY>");
			out.print("</HTML>");
			out.flush();
			out.close();
		}else {
			response.sendRedirect("admin/navList.jsp");
		}
	}

}
