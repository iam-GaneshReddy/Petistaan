package Repository;

import Model.OwnerDTO;
import Util.InputUtil;
import Util.MapperUtil;
import enums.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RepositoryImpl implements Repository {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Petistaan_jdbc";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Gani@123";

    @Override
    public void add(OwnerDTO owner) {
        String sql = "INSERT INTO owner_table "
                + "(id, first_name, last_name, gender, city, state, mobile_number, email_id, "
                + "pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type) " + "VALUES (" + owner.getId() + " , '"
                + owner.getFirstName() + "' , '" + owner.getLastName() + "' , '" + owner.getGender().toString()
                + "' , '" + owner.getCity() + "' , '" + owner.getState() + "' , '" + owner.getMobileNumber() + "' , '"
                + owner.getEmailId() + "' , " + owner.getPetId() + " , '" + owner.getPetName() + "' , '"
                + Date.valueOf(owner.getPetBirthDate()) + "' , '" + owner.getPetGender().toString() + "' , '"
                + owner.getPetType().toString() + "')";
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            // System.out.println("successfully connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(statement))
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (Objects.nonNull(connection))
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Optional<OwnerDTO> getById(int id) {
        String sql = "select * from owner_table where id=" + id;
        OwnerDTO owner = null;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(statement))
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (Objects.nonNull(connection))
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(owner);
    }

    public void updatePetDetails(int ownerId, String petName) {
        String sql = "update owner_table SET pet_name= '" + petName + "' WHERE id = " + ownerId;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            //System.out.println("successfully connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(statement))
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (Objects.nonNull(connection))
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int ownerId) {
        String sql = "delete from owner_table where id=" + ownerId;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            // System.out.println("successfully connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(statement))
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (Objects.nonNull(connection))
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        String sql = "select * from owner_table";
        List<OwnerDTO> list = new ArrayList<>();
        OwnerDTO owner = null;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
                list.add(owner);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(statement))
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (Objects.nonNull(connection))
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
