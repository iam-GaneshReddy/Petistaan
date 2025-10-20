package Repository;
//Prepare Repository
import Config.DatabaseConfig;
import Config.PropertiesConfig;
import Model.OwnerDTO;
import Util.MapperUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryImpl2 implements Repository{
    private static final String DATABASE_DRIVER = "database.driver";
    private static final PropertiesConfig PROPERTIES_CONFIG =PropertiesConfig.getInstance();

    @Override
    public void add(OwnerDTO owner) {
        String sql= """
                Insert into owner_table (id, first_name, last_name, gender, city, state, mobile_number, email_id, 
                pet_id, pet_name, pet_date_of_birth, pet_gender, pet_type) 
                values (?,?,?,?,?,?,?,?,?,?,?,?,?);
                """;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement =  connection.prepareStatement(sql);){
            Class.forName( PROPERTIES_CONFIG.getProperty(DATABASE_DRIVER));
           preparedStatement.setInt(1,owner.getId());
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Optional<OwnerDTO> getById(int id) {
        String sql = "select * from owner_table where id=?";
        OwnerDTO owner = null;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            Class.forName(PROPERTIES_CONFIG.getProperty(DATABASE_DRIVER));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(owner);
    }

    @Override
    public void delete(int ownerId) {
        String sql = "delete from owner_table where id=?";
        try(Connection connection = DatabaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Class.forName(PROPERTIES_CONFIG.getProperty(DATABASE_DRIVER));
            preparedStatement.setInt(1, ownerId);
             preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        String sql = "select * from owner_table";
        List<OwnerDTO> list = new ArrayList<>();
        OwnerDTO owner = null;
        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()){
            Class.forName(PROPERTIES_CONFIG.getProperty(DATABASE_DRIVER));
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                owner = MapperUtil.convertOwnerResultSetToDto(resultSet);
                list.add(owner);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updatePetDetails(int ownerId, String petName) {
        String sql="update owner_table set pet_name = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            Class.forName(PROPERTIES_CONFIG.getProperty(DATABASE_DRIVER));
            preparedStatement.setString(1, petName);
            preparedStatement.setInt(2, ownerId);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
           // throw new InternalServiceException(exception.getMessage());
        }
    }

    @Override
    public List<OwnerDTO> updatePetDetails(String petType) {
        String sql ="call add_prefix_to_pet_name(?)";
        List<OwnerDTO> list =new ArrayList<>();
        OwnerDTO owner = null;
        try(Connection connection = DatabaseConfig.getConnection();
            CallableStatement callableStatement= connection.prepareCall(sql)){
                Class.forName(PROPERTIES_CONFIG.getProperty(DATABASE_DRIVER));
            callableStatement.setString(1, petType);
            ResultSet resultSet=callableStatement.executeQuery();
            while(resultSet.next()){
                owner=MapperUtil.convertOwnerResultSetToDto(resultSet);
                list.add(owner);
            }
            }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
