package DAO;

import model.Category;
import model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements GeneralDAO<Department>{
    private SQLConnection sqlConnection = new SQLConnection();
    private final String FIND_ALL = "SELECT * FROM department;";
    @Override
    public List<Department> findAll() throws SQLException, ClassNotFoundException {
        List<Department> departments = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("departmentId");
            String name = resultSet.getString("departmentName");
            departments.add(new Department(id, name));
        }
        return departments;
    }

    @Override
    public Department findById(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Department> findByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void add(Department department) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void edit(int id, Department department) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {

    }
}
