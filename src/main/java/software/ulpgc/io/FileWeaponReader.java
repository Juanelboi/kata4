package software.ulpgc.io;

import software.ulpgc.model.Weapon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWeaponReader implements WeaponReader {
    private final File file;
    private final WeaponDeserializer deserializer;

    public FileWeaponReader(File file, WeaponDeserializer deserializer) {
        this.file = file;
        this.deserializer = deserializer;
    }


    private void SkipFirstLine(BufferedReader reader) throws IOException {
        reader.readLine();
    }
}
