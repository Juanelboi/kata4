package software.ulpgc.io;

import software.ulpgc.model.Weapon;

import java.sql.SQLException;

public interface WeaponWriter extends AutoCloseable{
    void write(Weapon weapon) throws SQLException;
}
