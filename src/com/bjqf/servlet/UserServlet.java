package com.bjqf.servlet;

import com.bjqf.entity.Role;
import com.bjqf.entity.User;
import com.bjqf.exception.UserException;
import com.bjqf.service.PaperService;
import com.bjqf.service.UserService;
import com.bjqf.util.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    UserService userService = new UserService();
    PaperService paperService = new PaperService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String obj = req.getParameter("obj");
        if(obj.equals("login")){
            login(req, resp);
        }else if(obj.equals("logout")){
            logout(req, resp);
        }else if(obj.equals("selectAll")) {
            selectAll(req, resp);
        }else if(obj.equals("selectRole")) {
            selectRole(req, resp);
        }else if(obj.equals("addUser")){
            addUser(req, resp);
        }else if(obj.equals("selectByUsername")){
            selectByUsername(req, resp);
        }else if(obj.equals("updateUser")){
            updateUser(req, resp);
        }else if(obj.equals("pageLogin")) {
            pageLogin(req, resp);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");
        try {
            List<User> list = userService.login(username, userpwd);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", list.get(0));

            if(list.get(0).getRolename().equals("超级管理员")){
                response.sendRedirect("index.jsp");
            }else if(list.get(0).getRolename().equals("学生")){
                pageLogin(request, response);
            }
        } catch (UserException e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        response.sendRedirect("login.jsp");
    }

    private void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = 3;
        PageModel pageModel = userService.queryByPage(pageNo, pageSize);

        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("sys/user/list.jsp").forward(request, response);
    }

    private void selectRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Role> list = userService.queryRole();
        request.setAttribute("list", list);
        request.getRequestDispatcher("sys/user/add.jsp").forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int roleid = Integer.parseInt(request.getParameter("roleid"));
//        String username = request.getParameter("username");
//        String userpwd = request.getParameter("userpwd");
//        String usertruename = request.getParameter("usertruename");
//
//        User user = new User();
//        user.setRoleid(roleid);
//        user.setUsername(username);
//        user.setUserpwd(userpwd);
//        user.setUsertruename(usertruename);
//
//        try{
//            userService.addUser(user);
//            response.sendRedirect("UserServlet?obj=selectAll&pageNo=1");
//        }catch (UserException e){
//            HttpSession session = request.getSession(true);
//            session.setAttribute("error", e.getMessage());
//
//            response.sendRedirect("sys/user/add.jsp");
//        }
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        String username = request.getParameter("username");
        String userpwd = request.getParameter("userpwd");
        String usertruename = request.getParameter("usertruename");
        boolean userstate = Boolean.parseBoolean(request.getParameter("userstate"));

        User user = new User();
        user.setRoleid(roleid);
        user.setUsername(username);
        user.setUserpwd(userpwd);
        user.setUsertruename(usertruename);
        user.setUserstate(userstate);

        try {
            userService.addUser(user);
            request.getRequestDispatcher("UserServlet?obj=selectAll&pageNo=1").forward(request, response);
        }catch (UserException e){
            request.setAttribute("usererror", e.getMessage());
            request.getRequestDispatcher("UserServlet?obj=selectRole").forward(request, response);
        }
    }

    private void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        List<User> userList = userService.selectByUsername(username);
        request.setAttribute("user", userList.get(0));

        List<Role> roleList = userService.queryRole();
        request.setAttribute("roleList", roleList);

        request.getRequestDispatcher("sys/user/edit.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int roleid = Integer.parseInt(request.getParameter("roleid"));
//        String username = request.getParameter("username");
//        String userpwd = request.getParameter("userpwd");
//        String usertruename = request.getParameter("usertruename");
//
//        User user = new User();
//        //user.setUserid(1);
//        user.setRoleid(roleid);
//        user.setUsername(username);
//        user.setUserpwd(userpwd);
//        user.setUsertruename(usertruename);
//
//        HttpSession session = request.getSession(true);
//
//        try{
//            userService.updateUser(user);
//            session.removeAttribute("error");
//            response.sendRedirect("UserServlet?obj=selectAll&pageNo=1");
//        }catch (UserException e){
//            session.setAttribute("error", e.getMessage());
//            response.sendRedirect("UserServlet?obj=selectByUsername&username=" + username);
//        }
        String username = request.getParameter("username");
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        String userpwd = request.getParameter("userpwd");
        String usertruename = request.getParameter("usertruename");
        boolean userstate = Boolean.parseBoolean(request.getParameter("userstate"));

        User user = new User();
        user.setRoleid(roleid);
        user.setUsername(username);
        user.setUserpwd(userpwd);
        user.setUsertruename(usertruename);
        user.setUserstate(userstate);

        userService.updateUser(user);
        request.getRequestDispatcher("UserServlet?obj=selectAll&pageNo=1").forward(request, response);
    }

    private void pageLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        PageModel pageModel = paperService.queryByPage(pageNo, 3);
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("user/index.jsp").forward(request, response);
    }
}