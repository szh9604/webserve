package online.zhihao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

/**
 * Created by Super_hao on 2017/4/7.
 */
@WebServlet(name = "backname")
public class backname extends HttpServlet {
    private ServletContext serve;
    Connection conn = null;
    PreparedStatement stmt = null;

    @Override
    public void destroy() {
        super.destroy();
        // 关闭连接
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("开始连接数据库");
        final String jdbcDriver = "com.mysql.jdbc.Driver";
        final String dbUrl = "jdbc:mysql://localhost/test";
        final String user = "root";
        final String pass = "szh960411";
        try {
            // 注册驱动
            Class.forName("com.mysql.jdbc.Driver");


            // 连接数据库
            conn = DriverManager.getConnection(dbUrl, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("user");
        String pass = request.getParameter("name");


        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        if (addUser(out, name, pass)) {
            out.print("OK");
            connectMysql(out);
        } else
            out.print("False");

    }

    public void connectMysql(PrintWriter out) throws ServletException {
        // name and rul


        long time = new Date().getTime();
        try {


            // 创建查询

            String sql = "select AdminNum,AdminPass from Adminuser";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            out.print("<table border='2'><tr><th>姓名</th><th>密码</th></tr>");
            // 获取数据
            while (rs.next()) {
                String num = rs.getString("AdminNum");
                String adpass = rs.getString("AdminPass");
                out.print("<tr><td>" + num + "</td><td>" + adpass + "</td></tr>");
            }
            out.print("</table>");
            int[] x=new int[100];


            // 关闭连接
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(PrintWriter out, String a, String b) {
        System.out.println(a+b);

        try {

            String sql = "INSERT INTO Adminuser VALUE (?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, a);
            stmt.setString(2, b);
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();


        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        connectMysql(out);


    }
}
