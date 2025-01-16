package software.ulpgc.io;

import software.ulpgc.model.Weapon;

public interface WeaponWriter extends AutoCloseable{
    void write(Weapon weapon);
}
