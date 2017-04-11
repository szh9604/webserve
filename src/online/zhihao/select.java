package online.zhihao;

import DAO.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Super_hao on 2017/4/10.
 */
@WebServlet(name = "select")
public class select extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao=new DAO();
        String sql="SELECT * FROM student WHERE age=?";
        List<student> list=dao.getForList(student.class,sql,18);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        for (int i = 0; i < list.size(); i++) {
            out.print("<strong>"+list.get(i)+"</strong><br>");
        }
        sql="SELECT AdminNum adn,AdminPass adp FROM adminuser WHERE AdminNum=?";
        adminuser admin=dao.get(adminuser.class,sql,"1239");
        out.print("<strong>"+admin+"</strong>");
    }
}
