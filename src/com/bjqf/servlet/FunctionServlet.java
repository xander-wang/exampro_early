package com.bjqf.servlet;

import com.bjqf.entity.FunRole;
import com.bjqf.entity.Function;
import com.bjqf.exception.FunctionException;
import com.bjqf.service.FunctionService;
import com.bjqf.service.RoleService;
import com.bjqf.util.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FunctionServlet")
public class FunctionServlet extends HttpServlet {
    FunctionService functionService = new FunctionService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String obj = req.getParameter("obj");
        if(obj.equals("selectAll")) {
            selectAll(req, resp);
        }else if(obj.equals("toAdd")) {
            toAdd(req, resp);
        }else if(obj.equals("addFunction")) {
            addFunction(req, resp);
        }else if(obj.equals("selectByFunid")) {
            selectByFunid(req, resp);
        }else if(obj.equals("updateFunction")) {
            updateFunction(req, resp);
        }else if(obj.equals("selectFunRole")) {
            selectFunRole(req, resp);
        }
    }

    private void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = 3;

        PageModel pageModel = new PageModel();
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("sys/function/list.jsp").forward(request, response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int funpid = Integer.parseInt(request.getParameter("funpid"));
        String funpname = request.getParameter("funpname");

        request.setAttribute("funpname", funpname);
        request.setAttribute("funpid", funpid);
        request.getRequestDispatcher("sys/function/add.jsp").forward(request, response);
    }

    private void addFunction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int funpid = Integer.parseInt(request.getParameter("funpid"));
        String funpname = request.getParameter("funpname");
        String funname = request.getParameter("funname");
        String funurl = request.getParameter("funurl");
        boolean funstate = Boolean.parseBoolean(request.getParameter("funstate"));

        Function function = new Function();
        function.setFunname(funname);
        function.setFunpid(funpid);
        function.setFunurl(funurl);
        function.setFunstate(funstate);

        try {
            functionService.addFunction(function);
            request.getRequestDispatcher("FunctionServlet?obj=selectAll&pageNo=1").forward(request, response);
        } catch (FunctionException e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("FunctionServlet?obj=toAdd&funpid="+funpid+"&funpname"+funpname).forward(request, response);
        }
    }

    private void selectByFunid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int funid = Integer.parseInt(request.getParameter("funid"));
        Function function = functionService.selectByFunid(funid).get(0);

        request.setAttribute("function", function);
        request.getRequestDispatcher("sys/function/edit.jsp").forward(request, response);
    }

    private void updateFunction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int funid = Integer.parseInt(request.getParameter("funid"));
        String funname = request.getParameter("funname");
        String funurl = request.getParameter("funurl");
        boolean funstate = Boolean.parseBoolean(request.getParameter("funstate"));

        Function function = new Function();
        function.setFunid(funid);
        function.setFunname(funname);
        function.setFunurl(funurl);
        function.setFunstate(funstate);

        try {
            functionService.updateFunction(function);

            request.getRequestDispatcher("FunctionServlet?obj=selectAll&pageNo=1").forward(request, response);
        } catch (FunctionException e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("FunctionServlet?obj=selectByFunid&funid="+funid).forward(request, response);
        }
    }

    private void selectFunRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        List<FunRole> list = functionService.selectFunRole(roleid);

        request.setAttribute("list", list);
        request.getRequestDispatcher("sys/role/right.jsp").forward(request, response);
    }
}
