package controller;

import DAO.CategoryDAO;
import DAO.DepartmentDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import model.Category;
import model.Department;
import model.Product;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showFormCreate(request, response);
                    break;
                case "edit":
                    showFormEdit(request, response);
                    break;
                case "delete":
                    showFormDelete(request, response);
                    break;
                default:
                    findAll(request, response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    create(request, response);
                    break;
                case "edit":
                    update(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                case "search":
                    searchByName(request, response);
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("User/list_user.jsp");
        List<User> users = userDAO.findAll();
        request.setAttribute("users", users);
        List<Department> departments = departmentDAO.findAll();
        request.setAttribute("departments", departments);
        requestDispatcher.forward(request, response);
    }
    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("User/create_user.jsp");
        List<Department> departments = departmentDAO.findAll();
        request.setAttribute("departments", departments);
        requestDispatcher.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String departmentName = request.getParameter("department");
        List<Department> list = departmentDAO.findAll();
        int departmentId = 0;
        for (Department department : list) {
            if (departmentName.equals(department.getName())) {
                departmentId = department.getId();
            }
        }
        User user = new User(name, dob, address, phone, email, departmentId);
        userDAO.add(user);
        findAll(request, response);
    }
    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("User/update_user.jsp");
        User user = userDAO.findById(id);
        request.setAttribute("user", user);
        List<Department> departments = departmentDAO.findAll();
        request.setAttribute("departments", departments);
        requestDispatcher.forward(request, response);
    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.findById(id);
        user.setName(request.getParameter("name"));
        user.setDob(request.getParameter("dob"));
        user.setAddress(request.getParameter("address"));
        user.setPhone(request.getParameter("phone"));
        user.setEmail(request.getParameter("email"));
        String departmentName = request.getParameter("department");
        int departmentId = 0;
        List<Department> list = departmentDAO.findAll();
        for (Department department : list) {
            if (departmentName.equals(department.getName())) {
                departmentId = department.getId();
            }
        }
        user.setDepartmentId(departmentId);
        userDAO.edit(id, user);
        findAll(request, response);
    }

    private void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.findById(id);
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("User/delete_user.jsp");
        requestDispatcher.forward(request, response);
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.delete(id);
        findAll(request, response);
    }
    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("name");
        List<User> list = userDAO.findByName(name);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("User/list_user.jsp");
        request.setAttribute("users",list);
        List<Department> departments = departmentDAO.findAll();
        request.setAttribute("departments", departments);
        requestDispatcher.forward(request,response);
    }

}
