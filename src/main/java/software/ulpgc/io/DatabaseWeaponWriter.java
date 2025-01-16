package software.ulpgc.io;

import software.ulpgc.model.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWeaponWriter implements WeaponWriter{
    private final Connection connection;
    private final PreparedStatement InsertPreparedWeaponStatement;

    public DatabaseWeaponWriter(Connection connection) throws SQLException {
        this.connection = connection;
        this.stopAutocommit();
        this.createTable();
        this.InsertPreparedWeaponStatement = insertPreparedWeaponStatement();
    }


    private final String createTableStatement = """
        CREATE TABLE IF NOT EXISTS weapons(
                    name TEXT PRIMARY KEY,
                    type TEXT NOT NULL,
                    physicaldamage DOUBLE,
                    magicdamage DOUBLE,
                    firedamage DOUBLE,
                    lightningdamage DOUBLE,
                    holydamage DOUBLE,
                    Strength TEXT,
                    Dexterity TEXT,
                    Intelligence TEXT,
                    Faith TEXT,
                    Arcane TEXT)
    """;

    private void createTable() {

    }

    private PreparedStatement insertPreparedWeaponStatement() {
    }


    @Override
    public void write(Weapon weapon) {

    }

    private void stopAutocommit() throws SQLException {
    connection.setAutoCommit(false);

    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
