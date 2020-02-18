package com.bjqf.servlet;

import com.bjqf.entity.Paper;
import com.bjqf.entity.Subject;
import com.bjqf.exception.PaperException;
import com.bjqf.service.PaperService;
import com.bjqf.util.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/PaperServlet")
public class PaperServlet extends HttpServlet {
    PaperService paperService = new PaperService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String obj = req.getParameter("obj");
        if(obj.equals("selectAll")){
            selectAll(req, resp);
        }else if(obj.equals("addPaper")){
            addPaper(req, resp);
        }else if(obj.equals("studentPaper")){
            studentPaper(req, resp);
        }else if(obj.equals("selectSub")) {
            selectSub(req, resp);
        }
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = 2;

        PageModel pageModel = paperService.queryByPage(pageNo, pageSize);

        req.setAttribute("pageModel", pageModel);
        req.getRequestDispatcher("sys/paper/list.jsp").forward(req, resp);
    }

    private void addPaper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pname = req.getParameter("pname");
        int number = Integer.parseInt(req.getParameter("scount"));

        HttpSession session = req.getSession(true);
        try {
            paperService.addPaper(pname, number);
            session.removeAttribute("error2");
            req.getRequestDispatcher("PaperServlet?obj=selectAll&pageNo=1").forward(req, resp);
        } catch (PaperException e) {
            session.setAttribute("error2", e.getMessage());
            resp.sendRedirect("sys/paper/add.jsp");
        }
    }

    private void selectSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pname = request.getParameter("pname");
        List<Subject> list = paperService.selectSub(pname);
        request.setAttribute("list", list);
        request.getRequestDispatcher("sys/paper/subjects.jsp").forward(request, response);
    }

    private void studentPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pname = request.getParameter("pname");
        int count = Integer.parseInt(request.getParameter("count"));

        List<Subject> list = paperService.selectSub(pname);

        request.setAttribute("list", list);
        request.setAttribute("pname", pname);
        request.setAttribute("count", count);

        request.getRequestDispatcher("user/paper/paper.jsp").forward(request, response);
    }

}
