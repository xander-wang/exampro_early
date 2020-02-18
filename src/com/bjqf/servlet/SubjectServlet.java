package com.bjqf.servlet;

import com.bjqf.entity.Subject;
import com.bjqf.entity.User;
import com.bjqf.exception.SubjectException;
import com.bjqf.service.SubjectService;
import com.bjqf.util.PageModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/SubjectServlet")
public class SubjectServlet extends HttpServlet{
    SubjectService subjectService = new SubjectService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String obj = req.getParameter("obj");
        if(obj.equals("selectAll")){
            selectAll(req, resp);
        }else if(obj.equals("addSubject")) {
            addSubject(req, resp);
        }else if(obj.equals("selectBySid")){
            selectBySid(req, resp);
        }else if(obj.equals("updateSubject")){
            updateSubject(req, resp);
        }
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = 2;
        PageModel pageModel = subjectService.queryByPage(pageNo, pageSize);

        req.setAttribute("pageModel", pageModel);
        req.getRequestDispatcher("sys/subject/list.jsp").forward(req, resp);
    }

    private void addSubject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scontent = req.getParameter("scontent");
        String sa = req.getParameter("sa");
        String sb = req.getParameter("sb");
        String sc = req.getParameter("sc");
        String sd = req.getParameter("sd");
        String skey = req.getParameter("skey");
        String sstate = req.getParameter("sstate");

        boolean state = true;
        if(sstate.equals("1")){
            state = true;
        }else if(sstate.equals("0")){
            state = false;
        }

        Subject subject = new Subject();
        subject.setScontent(scontent);
        subject.setSa(sa);
        subject.setSb(sb);
        subject.setSc(sc);
        subject.setSd(sd);
        subject.setSkey(skey);
        subject.setSstate(state);
        try {
            subjectService.addSubject(subject);
            resp.sendRedirect("SubjectServlet?obj=selectAll&pageNo=1");
        }catch (SubjectException e) {
            HttpSession session = req.getSession(true);
            session.setAttribute("error", e.getMessage());

            resp.sendRedirect("sys/subject/add.jsp");
        }
    }

    private void selectBySid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));

        List<Subject> list = subjectService.selectBySid(sid);

        req.setAttribute("subject", list.get(0));
        req.getRequestDispatcher("sys/subject/edit.jsp").forward(req, resp);
    }

    private void updateSubject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));
        String scontent = req.getParameter("scontent");
        String sa = req.getParameter("sa");
        String sb = req.getParameter("sb");
        String sc = req.getParameter("sc");
        String sd = req.getParameter("sd");
        String skey = req.getParameter("skey");
        boolean sstate = Boolean.parseBoolean(req.getParameter("sstate"));

        Subject subject = new Subject();
        subject.setSid((sid));
        subject.setScontent(scontent);
        subject.setSa(sa);
        subject.setSb(sb);
        subject.setSc(sc);
        subject.setSd(sd);
        subject.setSkey(skey);
        subject.setSstate(sstate);

        HttpSession session = req.getSession(true);

        try{
            subjectService.updateSubject(subject);
            session.removeAttribute("error");
            resp.sendRedirect("SubjectServlet?obj=selectAll&pageNo=1");
        }catch (SubjectException e){
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("SubjectServlet?obj=selectBySid&sid=" + sid);
        }

    }
}
