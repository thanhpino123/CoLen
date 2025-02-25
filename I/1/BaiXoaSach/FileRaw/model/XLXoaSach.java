/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package model;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import model.KetNoi;
import model.sach1;
import java.util.ArrayList;


@WebServlet(name="XLXoaSach", urlPatterns={"/XLXoaSach"})
public class XLXoaSach extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet XLXoaSach</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet XLXoaSach at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
       
           String MaSach = request.getParameter("MaSach");
           ArrayList<sach1> dsSach = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        String message = null;
        String messageType = null;

        try {
         // Kết nối với CSDL
            Class.forName("com.mysql.cj.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlsach","root","");

            // Kiểm tra xem mã sách có tồn tại không
            ps = conn.prepareStatement("SELECT COUNT(*) FROM Sach1 WHERE MaSach = ?");
            ps.setString(1, MaSach);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                // Nếu tồn tại, xóa sách
                ps = conn.prepareStatement("DELETE FROM Sach1 WHERE MaSach = ?");
                ps.setString(1, MaSach);
                ps.executeUpdate();

                message = "Xóa sách thành công với mã: " + MaSach;
                messageType = "success";
            } else {
                // Nếu không tồn tại
                message = "Không tồn tại sách có mã: " + MaSach;
                messageType = "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Lỗi hệ thống: " + e.getMessage();
            messageType = "error";
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Gửi thông báo về lại trang XoaSach.jsp
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("XoaSach.jsp");
        dispatcher.forward(request, response);
    
       
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
