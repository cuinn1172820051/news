package com.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class ArticleListServlet
 */
@WebServlet(name="ArticleListServlet", urlPatterns="/articleList.html")
public class ArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int p = MyFuns.string2Int(request.getParameter("p"));
		String nav_id = request.getParameter("nav_id");
		SqlHepler sqlHelper = new SqlHepler();
		//获取导航
		List <Nav>list = sqlHelper.queryNavAll();
		//获取最新发布的新闻数据
		List <Article>articleList = sqlHelper.queryLastArticles(); 
		//获取分类下的新闻列表
		List <ArticleNav>articles = sqlHelper.queryArticleByPage(p, nav_id, null);
		//查询总的记录数
		int rows = sqlHelper.queryArticleCount(nav_id, null);
		Nav nav = sqlHelper.queryNav_name(nav_id);
		request.setAttribute("navList",list);
		request.setAttribute("articleList",articleList);
		request.setAttribute("articles",articles);
		request.setAttribute("rows",rows);
		request.setAttribute("p", p);
		request.setAttribute("nav_id",nav_id);
		request.setAttribute("nav",nav);
		sqlHelper.destroy();
		request.getRequestDispatcher("list.jsp").forward(request,response);//把数据传送到list.jsp页面
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
