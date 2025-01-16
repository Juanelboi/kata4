package software.ulpgc.io;

import software.ulpgc.model.Weapon;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static java.sql.DriverManager.getConnection;
import static java.sql.Types.DOUBLE;
import static java.sql.Types.NVARCHAR;

public class DatabaseWeaponWriter implements WeaponWriter{

    private final Connection connection;
    private final PreparedStatement InsertPreparedWeaponStatement;


    public static DatabaseWeaponWriter open(File file) throws SQLException {
        return new DatabaseWeaponWriter("jdbc:sqlite:"+file.getAbsolutePath());
    }

    private DatabaseWeaponWriter(String connection) throws SQLException {
        this(getConnection(connection));
    }

    public DatabaseWeaponWriter(Connection connection) throws SQLException {
        this.connection = connection;
        this.stopAutocommit();
        this.createTable();
        InsertPreparedWeaponStatement = createWeaponStatement();
    }



    private final String CreateTableStatement= """
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
    private void createTable() throws SQLException {
        connection.createStatement().execute(CreateTableStatement);
    }


    private final String InsertWeaponStatement= """
            INSERT OR IGNORE INTO weapons (name,
                                            type,
                                            physicaldamage,
                                            magicdamage,
                                            firedamage,
                                            lightningdamage,
                                            holydamage,
                                            Strength,
                                            Dexterity,
                                            Intelligence,
                                            Faith,
                                            Arcane)
                        VALUES(?,?,?,?,?,?,?,?,?,?,?,?)
            """;
    private PreparedStatement createWeaponStatement() throws SQLException {
        return connection.prepareStatement(InsertWeaponStatement);
    }


    @Override
    public void write(Weapon weapon) throws SQLException {
        insertWeaponStatement(weapon).execute();
    }

    private PreparedStatement insertWeaponStatement(Weapon weapon) throws SQLException {
        InsertPreparedWeaponStatement.clearParameters();
        paramerterOf(weapon).forEach(this::define);
        return InsertPreparedWeaponStatement;
    }

    private void define(Parameter parameter) {
        try {
            if (parameter.value == null) {
                InsertPreparedWeaponStatement.setNull(parameter.index, parameter.type);
            } else {
                InsertPreparedWeaponStatement.setObject(parameter.index, parameter.value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Parameter> paramerterOf(Weapon weapon) {
        return List.of(
                new Parameter(1,weapon.name(),NVARCHAR),
                new Parameter(2,weapon.type(),NVARCHAR),
                new Parameter(3,weapon.physicalDamage(),DOUBLE),
                new Parameter(4,weapon.physicalDamage(),DOUBLE),
                new Parameter(5,weapon.physicalDamage(),DOUBLE),
                new Parameter(6,weapon.physicalDamage(),DOUBLE),
                new Parameter(7,weapon.physicalDamage(),DOUBLE),
                new Parameter(8,weapon.Strength(),NVARCHAR),
                new Parameter(9,weapon.Dexterity(),NVARCHAR),
                new Parameter(10,weapon.Intelligence(),NVARCHAR),
                new Parameter(11,weapon.Faith(),NVARCHAR),
                new Parameter(12,weapon.Arcane(),NVARCHAR)
        );
    }

    private record Parameter(int index, Object value, int type){}

    @Override
    public void close() throws Exception {
        connection.commit();
        connection.close();
    }

    private void stopAutocommit() throws SQLException {
        connection.setAutoCommit(false);
    }
}
