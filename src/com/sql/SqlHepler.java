package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.*;
import com.entity.Article;
import com.entity.ArticleNav;
import com.entity.Manager;
import com.entity.Nav;
import com.tools.MyFuns;
public class SqlHepler {
	private int perPage = 10;
	private Connection conn=null;
	public SqlHepler() {
		conn=DB.getConnection(); 
	}
	public void destroy() {
		if(conn!=null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public Manager queryManagerById(String manager_id) {
		String sql = "select manager_id,manager_name, manager_pwd from manager where manager_id=?";
		Manager manager = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				manager = new Manager();
				manager.setManager_id(rs.getString("manager_id"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setManager_pwd(rs.getString("manager_pwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manager;
	}
	public Manager queryManagerByIdAndPwd(Manager m) {
		String sql = "select manager_id,manager_name, manager_pwd from manager where manager_id=? and manager_pwd=?";
		Manager manager = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getManager_id());
			ps.setString(2, m.getManager_pwd());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				manager = new Manager();
				manager.setManager_id(rs.getString("manager_id"));
				manager.setManager_name(rs.getString("manager_name"));
				manager.setManager_pwd(rs.getString("manager_pwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manager;
	}
	
	public boolean insertManager(Manager manager) {
		String sql = "insert into manager(manager_id,manager_name,manager_pwd)values(?,?,?) ";
		boolean b = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_id());
			ps.setString(2,manager.getManager_name());
			ps.setString(3, manager.getManager_pwd());
			ps.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean insertNav(Nav nav) {
		String sql = "insert into nav(nav_id,nav_name,nav_feight)values(?,?,?) ";
		boolean b = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nav.getNav_id());
			ps.setString(2,nav.getNav_name());
			ps.setInt(3,nav.getNav_feight());
			ps.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean deleteManager(Manager manager) {
		String sql = "delete from manager where manager_id=?";
		boolean b = false;	
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_id());
			ps.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean updateManagerPwd(Manager manager) {
		String sql = "update manager set manager_pwd=? where manager_id=?";
		boolean b = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_pwd());
			ps.setString(2, manager.getManager_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean updateManagerName(Manager manager) {
		String sql = "update manager set manager_name=? where manager_id=?";
		boolean b = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_name());
			ps.setString(2, manager.getManager_id());
			ps.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean updateManagerNameAndPwd(Manager manager) {
		String sql = "update manager set manager_name=?,manager_pwd=? where manager_id=?";
		boolean b = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getManager_name());
			ps.setString(2, manager.getManager_pwd());
			ps.setString(3, manager.getManager_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public List<Manager> queryManagerAll() {
		List <Manager>list = new ArrayList();
		String sql = "select manager_id,manager_name from manager";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Manager manager = new Manager();
				manager.setManager_id(rs.getString("manager_id"));
				manager.setManager_name(rs.getString("manager_name"));
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Nav> queryNavAll(){
		List <Nav>list = new ArrayList();
		String sql = "select nav_id,nav_name,nav_feight from nav order by nav_feight asc";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);	
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Nav nav = new Nav();
				nav.setNav_id(rs.getString("nav_id"));
				nav.setNav_name(rs.getString("nav_name"));
				nav.setNav_feight(rs.getInt("nav_feight"));
				list.add(nav);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public Nav queryNavById(String nav_id) {
		String sql = "select nav_id,nav_name,nav_feight from nav where nav_id=?";
		Nav nav = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,nav_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				nav = new Nav();
				nav.setNav_id(rs.getString("nav_id"));
				nav.setNav_name(rs.getString("nav_name"));
				nav.setNav_feight(rs.getInt("nav_feight"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return nav;
	}
	public boolean updateNav(Nav nav) {
		String sql = "update nav set nav_name=?,nav_feight=? where nav_id=?";
		boolean b=false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,nav.getNav_name());
			ps.setInt(2,nav.getNav_feight());
			ps.setString(3,nav.getNav_id());
			ps.executeUpdate();
			b = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 删除新闻类别
	 * @param nav
	 * @return
	 */
	public boolean deleteNav(Nav nav) {
		String sql = "delete from nav where nav_id=?";
		boolean b = false;	
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nav.getNav_id());
			ps.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
		
	}
	/**
	 * 根据新闻类别查询新闻文章
	 * @param nav_id
	 * @return
	 */
	public List<Article> queryArticleByNav_id(String nav_id) {
		String sql = "select article_id,article_title,article_date,article_content,nav_id from article where nav_id=? order by article_date desc";
		List <Article>list = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,nav_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Article article = new Article();
				article.setArticle_content(rs.getString("article_content"));
				article.setArticle_date(rs.getString("article_date"));
				article.setArticle_id(rs.getString("article_id"));
				article.setArticle_title(rs.getString("article_title"));
				article.setNav_id(rs.getString("nav_id"));
				list.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public List<Article> queryArticleByNav_id4Index(String nav_id) {
		String sql = "select article_id,article_title,article_date,article_content,nav_id from article where nav_id=? order by article_date desc limit 0,10";
		List <Article>list = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,nav_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Article article = new Article();
				article.setArticle_content(rs.getString("article_content"));
				article.setArticle_date(rs.getString("article_date"));
				article.setArticle_id(rs.getString("article_id"));
				article.setArticle_title(rs.getString("article_title"));
				article.setNav_id(rs.getString("nav_id"));
				list.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * 增加新闻
	 * @param article
	 * @return
	 */
	public boolean insertArticle(Article article) {
		String sql = "insert into article(article_id,article_title,article_date,article_content,nav_id) values(?,?,?,?,?)"; 
		boolean b = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,article.getArticle_id());
			ps.setString(2,article.getArticle_title());
			ps.setString(3,article.getArticle_date());
			ps.setString(4,article.getArticle_content());
			ps.setString(5,article.getNav_id());
			ps.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * 通过Map得到两个类中的字段
	 * 分页查询
	 * @return
	 */
//	public List<Map> queryArticleMap() {
//		String sql = "select article_id,article_title,article_date,nav_name from article,nav where article.nav_id=nav.nav_id order by article_date desc";
//		List <Map>list = new ArrayList();
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				Map map = new HashMap();
//				map.put("article_title",rs.getString("article_title"));
//				map.put("article_id",rs.getString("article_id"));
//				map.put("article_date",rs.getString("article_date"));
//				map.put("nav_name",rs.getString("nav_name"));
//				list.add(map);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//空格导致sql语句语法错误 
	public List<ArticleNav> queryArticleByPage(int p) {
		int start = p*perPage;
		String sql = "select article_id,article_title,article_date,nav_name from article,nav where article.nav_id=nav.nav_id order by article_date desc limit "+start+","+perPage;
		ArticleNav art = null;
		List <ArticleNav>list = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				art = new ArticleNav();
				art.setArticle_date(rs.getString("article_date"));
				art.setArticle_id(rs.getString("article_id"));
				art.setArticle_title(rs.getString("article_title"));
				art.setNav_name(rs.getString("nav_name"));
				list.add(art);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 通过新闻类别、新闻标题查询
	 * @return
	 */
	public List<ArticleNav> queryArticleByPage(int p,String nav_id,String article_title) {
		int start = p*perPage;
		String sql = "select article_id,article_title,article_date,nav_name "
				+ "from article,nav where article.nav_id=nav.nav_id ";
				if(nav_id !=null && !"".equals(nav_id)) {
					sql+="and nav.nav_id=? ";
				}
				if(article_title !=null && !"".equals(article_title)) {
					sql+="and article_title like ?";
				}
				sql+= " order by article_date desc limit "+start+","+perPage;
		ArticleNav art = null;
		List <ArticleNav>list = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			int pos = 1;
			if(nav_id!=null && !"".equals(nav_id)) {
				ps.setString(1,nav_id);
				pos = 2;
			}
			if(article_title !=null && !"".equals(article_title)) {
				ps.setString(pos,"%"+article_title+"%");
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				art = new ArticleNav();
				art.setArticle_date(rs.getString("article_date"));
				art.setArticle_id(rs.getString("article_id"));
				art.setArticle_title(rs.getString("article_title"));
				art.setNav_name(rs.getString("nav_name"));
				list.add(art);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public int queryArticleCount() {
		String sql = "select count(*) from article";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	public int queryArticleCount(String nav_id,String article_title) {
		String sql = "select count(*) from article ";
		boolean f1 = nav_id !=null && !"".equals(nav_id);
		int rows = 0;
		int pos=0;
		if(f1) {
			sql="select count(*) from article,nav where article.nav_id=nav.nav_id and nav.nav_id=? ";
			pos = 1;
		}
		boolean f2 = article_title !=null && !"".equals(article_title);
		if(f2) {
			sql+= pos>0?"and":"where"  +" article_title like ?";
			pos+=1;
		}
		System.out.println(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if(f1) {
				ps.setString(1, nav_id);
			}
			if(f2) {
				ps.setString(pos, "%"+article_title+"%");
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				rows = rs.getInt(1);
				return rows;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	public Article queryArticleById(String article_id) {
		String sql = "select article_id,article_title,article_date,article_content,nav_id "
				+"from article where article_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,article_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Article a = new Article();
				a.setArticle_id(article_id);
				a.setArticle_date(rs.getString("article_date"));
				a.setArticle_content(rs.getString("article_content"));
				a.setArticle_title(rs.getString("article_title"));
				a.setNav_id(rs.getString("nav_id"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean editArticle(Article article) {
		String sql="update article "
				+ "set article_title=?,article_date=?,article_content=?,nav_id=? "
				+ "where article_id=?";
		boolean b = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, article.getArticle_title());
			ps.setString(2, article.getArticle_date());
			ps.setString(3, article.getArticle_content());
			ps.setString(4, article.getNav_id());
			ps.setString(5, article.getArticle_id());
			ps.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public boolean deleteArticle(Article a) {
		String sql = "delete from article where article_id=?";
		boolean b = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,a.getArticle_id());
			ps.executeUpdate();
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	public List<Article> queryLastArticles() {
		String sql = "select article_id,article_title from article order by article_date desc limit 0,10";
		List list = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Article art = new Article();
				art.setArticle_id(rs.getString("article_id"));
				art.setArticle_title(rs.getString("article_title"));
				list.add(art);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public Nav queryNav_name(String nav_id) {
		String sql = "select nav_name from nav where nav_id=?";
		Nav nav = new Nav();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,nav_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				nav.setNav_name(rs.getString("nav_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nav;
	}
}
