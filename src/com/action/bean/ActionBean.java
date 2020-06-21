package com.action.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.Article;
import com.entity.ArticleNav;
import com.entity.Manager;
import com.entity.Nav;
import com.sql.SqlHepler;

public class ActionBean {
	public String queryManagerAll() {
		SqlHepler sqlHepler = new SqlHepler();
		List <Manager>list = sqlHepler.queryManagerAll();
		String tableHtml = "";
		int i=1;
		for(Manager manager:list) {
			tableHtml += "<tr><td><input type='radio' name='rad' autocomplete='off' value='"+manager.getManager_id()+"'/></td><td class='text-center'>"+(i++)+"</td><td class='text-center'>"+manager.getManager_id()+"</td><td class='text-center'>"+manager.getManager_name()+"</td></tr>"; 
		}
		sqlHepler.destroy();
		return tableHtml;
	}
	public Manager queryManagerById(String manager_id) {
		SqlHepler sqlHepler = new SqlHepler();
		Manager manager = sqlHepler.queryManagerById(manager_id);
		sqlHepler.destroy();
		return manager;
	}
	/**
	 * 显示类别新闻
	 * @return
	 */
	public String queryNavAll() {
		SqlHepler sqlHepler = new SqlHepler();
		List <Nav>list = sqlHepler.queryNavAll();
		String tableHtml = "";
		int i=1;
		for(Nav nav:list) {
			tableHtml += "<tr><td><input type='radio' name='rad' autocomplete='off' value='"+nav.getNav_id()+"'/></td><td class='text-center'>"+(i++)+"</td><td class='text-center'>"+nav.getNav_name()+"</td><td class='text-center'>"+nav.getNav_feight()+"</td></tr>"; 
		}
		sqlHepler.destroy();
		return tableHtml;
	}
	public List<Nav> queryNavList(){
		SqlHepler sqlHepler = new SqlHepler();
		List <Nav>list = sqlHepler.queryNavAll();
		sqlHepler.destroy();
		return list;
	}
	public Nav queryNavById(String nav_id) {
		SqlHepler sqlHepler = new SqlHepler();
		Nav nav = sqlHepler.queryNavById(nav_id);
		sqlHepler.destroy();
		return nav;
	}
	public List<ArticleNav> queryArticleByPage(int p){
		SqlHepler sqlHepler = new SqlHepler();
		List <ArticleNav>list = sqlHepler.queryArticleByPage(p);
		sqlHepler.destroy();
		return list;
	}
	public List<ArticleNav> queryArticleByPage(int p,String nav_id,String article_title){
		SqlHepler sqlHepler = new SqlHepler();
		List <ArticleNav>list = sqlHepler.queryArticleByPage(p,nav_id,article_title);
		sqlHepler.destroy();
		return list;
	}
	public int queryCount(String nav_id,String article_title) {
		SqlHepler sqlHepler = new SqlHepler();
		int rows = sqlHepler.queryArticleCount(nav_id,article_title);
		return rows;
	}
	public Map queryArticleMap4Edit(String article_id) {
		Map map = new HashMap();
		SqlHepler sqlHepler = new SqlHepler();
		map.put("navList",sqlHepler.queryNavAll());//查询出所有的分类
		map.put("article",sqlHepler.queryArticleById(article_id));//根据article_id查询出article实体
		sqlHepler.destroy();
		return map;
	}
//	public Map queryArticleByPage(int p){
//		Map map = new HashMap();
//		SqlHepler sqlHepler = new SqlHepler();
//		map.put("list",sqlHepler.queryArticleByPage(p));
//		map.put("rows",sqlHepler.queryArticleCount());
//		sqlHepler.destroy();
//		return map;
//	}	
}
