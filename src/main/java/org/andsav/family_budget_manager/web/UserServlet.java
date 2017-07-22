package org.andsav.family_budget_manager.web;

import org.andsav.family_budget_manager.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private WebApplicationContext wac;

  @Override
  public void init() throws ServletException {
    super.init();
    wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    UserService us = wac.getBean(UserService.class);

    request.setAttribute("users", us.getAll());
    request.getRequestDispatcher("/userList.jsp").forward(request, response);
  }

}
