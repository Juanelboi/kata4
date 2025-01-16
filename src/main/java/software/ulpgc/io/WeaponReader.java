package software.ulpgc.io;

import software.ulpgc.model.Weapon;

import java.io.IOException;
import java.util.List;

public interface WeaponReader {
    Weapon read()throws IOException;
}
