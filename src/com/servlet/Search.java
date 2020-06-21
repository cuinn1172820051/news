package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Article;
import com.entity.ArticleNav;
import com.entity.Nav;
import com.sql.SqlHepler;
import com.tools.MyFuns;

/**
 * Servlet implementation class Search
 */
@WebServlet(name="Search", urlPatterns="/search.html")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTxt = request.getParameter("searchTxt");
		if(searchTxt==null||"".equals(searchTxt)) {
			response.sendRedirect("index.html");
			return;
		}

		int p = MyFuns.string2Int(request.getParameter("p"));
		SqlHepler sqlHelper = new SqlHepler();
		//获取导航菜单数据
		List <Nav>list = sqlHelper.queryNavAll();
		//获取最近发布的新闻
		List <Article>articleList = sqlHelper.queryLastArticles();
		//根据关键字进行模糊查询
		List <ArticleNav>articles = sqlHelper.queryArticleByPage(p, null, searchTxt);
		//根据关键词查询出总的记录数
		int rows = sqlHelper.queryArticleCount(null,searchTxt);
		sqlHelper.destroy();
		request.setAttribute("list",list);
		request.setAttribute("articleList", articleList);
		request.setAttribute("articles", articles);
		request.setAttribute("rows", rows);
		request.setAttribute("p", p);
		request.setAttribute("searchTxt",searchTxt);
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
