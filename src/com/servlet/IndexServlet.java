package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Nav;
import com.sql.SqlHepler;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(name="IndexServlet", urlPatterns="/index.html")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlHepler sqlHelper = new SqlHepler();
		//获取新闻类别
		List <Nav>list = sqlHelper.queryNavAll();
		//获取文章
		List <Map>resultList = new ArrayList();
		for(Nav nav : list) {
			Map map = new HashMap();
			map.put("articleList",sqlHelper.queryArticleByNav_id4Index(nav.getNav_id()));
			map.put("nav",nav);
			resultList.add(map);
		}
		sqlHelper.destroy();
		request.setAttribute("navList",list);
		request.setAttribute("resultList",resultList);
		request.getRequestDispatcher("index.jsp").forward(request,response);//把数据传送到index.jsp页面
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
