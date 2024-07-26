package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            final String SQL = "CREATE TABLE IF NOT EXISTS `mydbtest`.`users` (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(45) NOT NULL," +
                    "  `last_name` VARCHAR(45) NULL," +
                    "  `age` INT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)";
            statement.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            final String SQL = "DROP TABLE IF EXISTS `mydbtest`.`users`";
            statement.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String SQL = "INSERT INTO `mydbtest`.`users` (name, last_name, age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        final String SQL = "DELETE FROM `mydbtest`.`users` WHERE `id` = ?";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(SQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        final String SQL = "SELECT * FROM `mydbtest`.`users`";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("last_name"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
            users.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return users;
    }

    public void cleanUsersTable() {
        final String SQL = "DELETE FROM `mydbtest`.`users`";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(SQL);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
