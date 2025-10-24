package TransactionsRepository;

import Config.DatabaseConfig;
import Config.PropertiesConfig;
import Model.OwnerDTO;

import java.sql.*;
import java.util.List;
import java.util.Objects;

import static Config.DatabaseConfig.getConnection;

public class TransactionRepositoryImpl implements Repository {
    public static final String DATABASE_DRIVER = "database.driver";
    public static final PropertiesConfig PROPERTIES_CONFIG = PropertiesConfig.getInstance();

    @Override
    public void saveOwnersAutomatically(List<OwnerDTO> owners) {
        String sql = """
                        INSERT INTO owner_table
                (id, first_name, last_name, gender, city, state, mobile_number, email_id,
                        pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Class.forName(PROPERTIES_CONFIG.getProperty(DATABASE_DRIVER));
            for (OwnerDTO owner : owners) {
                preparedStatement.setInt(1, owner.getId());
                preparedStatement.setString(2, owner.getFirstName());
                preparedStatement.setString(3, owner.getLastName());
                preparedStatement.setString(4, owner.getGender().toString());
                preparedStatement.setString(5, owner.getCity());
                preparedStatement.setString(6, owner.getState());
                preparedStatement.setString(7, owner.getMobileNumber());
                preparedStatement.setString(8, owner.getEmailId());
                preparedStatement.setInt(9, owner.getPetId());
                preparedStatement.setString(10, owner.getPetName());
                preparedStatement.setDate(11, Date.valueOf(owner.getPetBirthDate()));
                preparedStatement.setString(12, owner.getPetGender().toString());
                preparedStatement.setString(13, owner.getPetType().toString());
                preparedStatement.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveOwnersManually(List<OwnerDTO> owners) {
        String sql = """
                INSERT INTO owner_table
                (id, first_name, last_name, gender, city, state, mobile_number, email_id,
                pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(PROPERTIES_CONFIG.getProperty(DATABASE_DRIVER));
            connection = DatabaseConfig.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (OwnerDTO owner : owners) {
                preparedStatement.setInt(1, owner.getId());
                preparedStatement.setString(2, owner.getFirstName());
                preparedStatement.setString(3, owner.getLastName());
                preparedStatement.setString(4, owner.getGender().toString());
                preparedStatement.setString(5, owner.getCity());
                preparedStatement.setString(6, owner.getState());
                preparedStatement.setString(7, owner.getMobileNumber());
                preparedStatement.setString(8, owner.getEmailId());
                preparedStatement.setInt(9, owner.getPetId());
                preparedStatement.setString(10, owner.getPetName());
                preparedStatement.setDate(11, Date.valueOf(owner.getPetBirthDate()));
                preparedStatement.setString(12, owner.getPetGender().toString());
                preparedStatement.setString(13, owner.getPetType().toString());
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (ClassNotFoundException | SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            exception.getMessage();
        } finally {
            try {
                if (Objects.nonNull(preparedStatement)) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveOwnersManuallyWithSavepoint(List<OwnerDTO> owners) {
        String sql = """
                INSERT INTO owner_table
                (id, first_name, last_name, gender, city, state, mobile_number, email_id,
                pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Savepoint savepoint = null;
        try {
            Class.forName(PROPERTIES_CONFIG.getProperty(DATABASE_DRIVER));
            connection = DatabaseConfig.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (OwnerDTO owner : owners) {
                preparedStatement.setInt(1, owner.getId());
                preparedStatement.setString(2, owner.getFirstName());
                preparedStatement.setString(3, owner.getLastName());
                preparedStatement.setString(4, owner.getGender().toString());
                preparedStatement.setString(5, owner.getCity());
                preparedStatement.setString(6, owner.getState());
                preparedStatement.setString(7, owner.getMobileNumber());
                preparedStatement.setString(8, owner.getEmailId());
                preparedStatement.setInt(9, owner.getPetId());
                preparedStatement.setString(10, owner.getPetName());
                preparedStatement.setDate(11, Date.valueOf(owner.getPetBirthDate()));
                preparedStatement.setString(12, owner.getPetGender().toString());
                preparedStatement.setString(13, owner.getPetType().toString());
                preparedStatement.executeUpdate();
                if (owner.getId() % 2 != 0) {
                    savepoint = connection.setSavepoint("Savepoint after ownerId " + owner.getId());
                }
            }
            connection.commit();
        } catch (ClassNotFoundException | SQLException exception) {
            try {
                connection.rollback(savepoint);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            exception.getMessage();
        } finally {
            try {
                if (Objects.nonNull(preparedStatement)) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    }



