package software.ulpgc.io;

import software.ulpgc.model.Weapon;

import java.io.IOException;
import java.util.List;

public interface WeaponReader {
    List<Weapon> load()throws IOException;
}
