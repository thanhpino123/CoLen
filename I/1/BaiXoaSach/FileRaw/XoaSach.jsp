<%-- 
    Document   : XoaSach.jsp
    Created on : Dec 9, 2024, 9:08:05 AM
    Author     : Trung Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<%@ page import="model.KetNoi" %>
<%@ page import="model.sach1" %>
<%@ page import="model.XLXoaSach" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xóa Sách</title>
    <style>
        /* CSS để trang trí */
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        form {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        input[type="text"] {
            width: 300px;
            padding: 10px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            text-align: center;
            padding: 10px;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        .message {
            text-align: center;
            font-weight: bold;
            margin: 20px 0;
        }
        .error {
            color: red;
        }
        .success {
            color: green;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Xóa Sách</h1>

    <form action="XLXoaSach" method="post">
        <input type="text" name="MaSach" placeholder="Nhập mã sách cần xóa" required>
        <button type="submit">Xóa</button>
    </form>

    <%-- Hiển thị thông báo --%>
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            String messageType = (String) request.getAttribute("messageType");
    %>
        <p class="message <%= messageType %>"><%= message %></p>
    <% } %>

    <%-- Hiển thị bảng sách --%>
    <table>
        <thead>
        <tr>
            <th>Mã Sách</th>
            <th>Tên Sách</th>
            <th>Số Lượng</th>
            <th>Đơn Giá</th>
            <th>Chiết Khấu</th>
        </tr>
        </thead>
        <tbody>
        <%
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
              ArrayList<sach1> dsSach = new ArrayList<>(); // Danh sách lưu trữ dữ liệu sach

            try {
            // Kết nối với CSDL
            Class.forName("com.mysql.cj.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlsach","root","");

                
                stmt = conn.createStatement();
                String sql = "SELECT * FROM sach1";
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String maSach = rs.getString("MaSach");
                    String tenSach = rs.getString("TenSach");
                    int soLuong = rs.getInt("SoLuong");
                    double donGia = rs.getDouble("DonGia");

                    double chietKhau = 0;
                    if (soLuong > 10) {
                        chietKhau = 0.05 * soLuong * donGia;
                    } else if (soLuong > 5) {
                        chietKhau = 0.03 * soLuong * donGia;
                    }
        %>
            <tr>
                <td><%= maSach %></td>
                <td><%= tenSach %></td>
                <td><%= soLuong %></td>
                <td><%= donGia %></td>
                <td><%= chietKhau %></td>
            </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
