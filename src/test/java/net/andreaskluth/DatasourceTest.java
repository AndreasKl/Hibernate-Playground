package net.andreaskluth;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DatasourceTest {

  @Autowired
  private DataSource dataSource;

  @Test
  public void works() throws SQLException {
    try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();) {
      statement.executeQuery("SELECT 1");
    }
  }

}
