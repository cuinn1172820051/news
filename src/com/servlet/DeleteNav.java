package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Article;
import com.entity.Nav;
import com.sql.SqlHepler;

/**
 * Servlet implementation class DeleteNav
 */
@WebServlet(name="DeleteNav", urlPatterns="/DeleteNav")
public class DeleteNav extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNav() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nav_id = request.getParameter("nav_id");
		if(!(nav_id==null||"".equals(nav_id))) {
			Nav nav = new Nav();
			nav.setNav_id(nav_id);
			SqlHepler sqlhepler = new SqlHepler();
			List <Article>list =  sqlhepler.queryArticleByNav_id(nav_id);
			if(list.size()<1) {
				boolean b = sqlhepler.deleteNav(nav);
			}else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.print("<HTML>");
				out.print("<HEAD><TITLE></TITLE></HEAD>");
				out.print("<BODY>");
				out.print("此新闻类别含有新闻，请谨慎删除。<a href=\"/news/admin/navList.jsp\">返回重新操作</a>");	
				out.print("</BODY>");
				out.print("</HTML>");
				out.flush();
				out.close();
			}
			sqlhepler.destroy();
		}
		response.sendRedirect("admin/navList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
