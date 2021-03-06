package com.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.conflg.Conflg;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		if(session.getAttribute(Conflg.MANAGER_ID)==null
				|| "".equals(session.getAttribute(Conflg.MANAGER_ID).toString())
				|| session.getAttribute(Conflg.LOGIN_SUCCESS)==null
				|| !Conflg.LOGIN_SUCCESS_VALUE.equals(session.getAttribute(Conflg.LOGIN_SUCCESS).toString())//login_success��ֵ��isloginֵ
		) {
			chain.doFilter(request,response);
			return;
		}
		res.sendRedirect("../login.jsp");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
