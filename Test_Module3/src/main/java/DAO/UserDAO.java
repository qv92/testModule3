package DAO;

import model.Product;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements GeneralDAO<User> {
    public UserDAO() {
    }

    private SQLConnection sqlConnection = new SQLConnection();
    private final String FIND_ALL_USER = "SELECT * FROM user;";
    private final String CREATE_USER = "INSERT INTO user(name, dob, address, phone, email, departmentId) values (?,?,?,?,?,?);";
    private final String FIND_BY_ID_USER = "SELECT * FROM user where id = ?;";
    private final String UPDATE_USER = "Update user set name = ?, dob = ?, address = ?, phone = ?, email =?, departmentId =? where id =?;";
    private final String DELETE_USER = "DELETE from user where id =?;";
    private final String FIND_BY_NAME_USER = "SELECT * FROM user where name like ?;";
    @Override
    public List<User> findAll() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USER);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String dob = resultSet.getString("dob");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            int departmentId = resultSet.getInt("departmentId");
            users.add(new User(id, name, dob, address, phone, email, departmentId));
        }
        return users;
    }

    @Override
    public User findById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_USER);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String dob = resultSet.getString("dob");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            int departmentId = resultSet.getInt("departmentId");
            return new User(id, name, dob, address, phone, email, departmentId);
        }
        return null;
    }

    @Override
    public List<User> findByName(String name) throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_USER);
        preparedStatement.setString(1,"%"+name+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String Username = resultSet.getString("name");
            String userdob = resultSet.getString("dob");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            int departmentId = resultSet.getInt("departmentId");
            users.add(new User(id, name, userdob, address, phone, email, departmentId));
        }
        return users;
    }

    @Override
    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getDob());
        preparedStatement.setString(3,user.getAddress());
        preparedStatement.setString(4,user.getPhone());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setInt(6, user.getDepartmentId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(int id, User user) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getDob());
        preparedStatement.setString(3,user.getAddress());
        preparedStatement.setString(4,user.getPhone());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setInt(6, user.getDepartmentId());
        preparedStatement.setInt(7,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
