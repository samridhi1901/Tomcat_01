package com.demo;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;


@WebServlet(
            description = "login Servlet Testing",
            urlPatterns = {"/LoginServlet"},
            initParams = {
                    @WebInitParam(name = "user",value = "samridhi"),
                    @WebInitParam(name = "password", value = "sam")
            }
    )
    public class LoginServlet extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
           String user = request.getParameter("user");
           String pwd = request.getParameter("pwd");
            String nameRegex="^[A-Z]{1}[a-z]{3,}$";
            String passRegex="(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%]).{8,20}";
           String userID =  getServletConfig().getInitParameter("user");
           String password = getServletConfig().getInitParameter("password");

            if((userID.equals(user) && password.equals(pwd))&& Pattern.matches(nameRegex,userID)&& Pattern.matches(passRegex,password)){
               request.setAttribute("user",user);
               request.getRequestDispatcher("LoginSuccess.jsp").forward(request,response);

           }else{
               RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
               PrintWriter out = response.getWriter();
               out.println("<font color = red>Either username or password is wrong</font>");
               rd.include(request,response);
           }

        }

    }

