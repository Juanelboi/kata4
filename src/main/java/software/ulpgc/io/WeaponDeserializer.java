package software.ulpgc.io;

import software.ulpgc.model.Weapon;

public interface WeaponDeserializer {
    Weapon deserialize(String line);
}
