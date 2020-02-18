package com.bjqf.servlet;

import com.bjqf.entity.StudentList;
import com.bjqf.entity.StudentPaper;
import com.bjqf.service.PaperErrorService;
import com.bjqf.service.StudentPaperService;
import com.bjqf.util.PageModel;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet("/StudentPaperServlet")
public class StudentPaperServlet extends HttpServlet {

    StudentPaperService studentPaperService = new StudentPaperService();
    PaperErrorService paperErrorService = new PaperErrorService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String obj = req.getParameter("obj");
        if(obj.equals("addStudentPaper")) {
            addStudentPaper(req, resp);
        }else if(obj.equals("score")) {
            score(req, resp);
        }else if(obj.equals("studentList")) {
            studentList(req, resp);
        }else if(obj.equals("selectAll")) {
            selectAll(req, resp);
        }
    }

    private void addStudentPaper(HttpServletRequest request, HttpServletResponse response) {
        try {
            StudentPaper studentPaper = new StudentPaper();

            // AJAX -> studentPaper
            BeanUtils.populate(studentPaper, request.getParameterMap());

            System.out.println(studentPaper.getSpid() + "\t" + studentPaper.getUserid() + "\t" + studentPaper.getSid()
                    + "\t" + studentPaper.getStudentkey() + "\t" + studentPaper.getStudentstate()
                    + "\t" + studentPaper.getPname());
            studentPaperService.addStudentPaper(studentPaper);
        }catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void score(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        String spid = request.getParameter("spid");

        int count = studentPaperService.score(userid, spid);

        PrintWriter out = response.getWriter();

        out.print("score: " + count*2);
        out.flush();
        out.close();
    }

    private void studentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        List<StudentList> list = studentPaperService.studentList(userid);

        request.setAttribute("list", list);
        request.getRequestDispatcher("user/paper/studentpaper.jsp").forward(request, response);
    }

    private void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        String spid = request.getParameter("spid");
        String pname = request.getParameter("pname");
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = 3;

        PageModel pageModel = paperErrorService.queryByPage(userid, spid, pageNo, pageSize);

        request.setAttribute("pname", pname);
        request.setAttribute("spid", spid);
        request.setAttribute("pageModel", pageModel);

        request.getRequestDispatcher("user/paper/studenterror.jsp").forward(request, response);
    }
}
