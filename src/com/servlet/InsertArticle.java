package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Article;
import com.sql.SqlHepler;
import com.tools.MyFuns;

/**
 * Servlet implementation class InsertArticle
 */
@WebServlet(name="InsertArticle", urlPatterns="/InsertArticle")
public class InsertArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertArticle() {
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
		String article_title   = request.getParameter("article_title");
		String article_date    = request.getParameter("article_date");
		String article_content = request.getParameter("content1");
		String nav_id          = request.getParameter("nav_id");
		if(article_title==null||article_date==null||nav_id==null) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.print("<HTML>");
			out.print("<HEAD><TITLE></TITLE></HEAD>");
			out.print("<BODY>");
			out.print("请将信息输入完整。<a href=\"/news/admin/addArticle.jsp\">返回重新输入</a>");	
			out.print("</BODY>");
			out.print("</HTML>");
			out.flush();
			out.close();
			return;
		}
		Article article = new Article();
		article.setArticle_content(MyFuns.convert2utf8(article_content));
		article.setArticle_date(article_date);
		article.setArticle_title(MyFuns.convert2utf8(article_title));
		article.setNav_id(nav_id);
		article.setArticle_id(UUID.randomUUID().toString());
		SqlHepler sqlhepler = new SqlHepler();
		boolean b = sqlhepler.insertArticle(article);
		if(!b) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.print("<HTML>");
			out.print("<HEAD><TITLE></TITLE></HEAD>");
			out.print("<BODY>");
			out.print("增加新闻错误。<a href=\"/admin/addArticle.jsp\">返回重新输入</a>");	
			out.print("</BODY>");
			out.print("</HTML>");
			out.flush();
			out.close();
		}else {
			response.sendRedirect("admin/articleList.jsp");
		}
		sqlhepler.destroy();
		
	}

}
