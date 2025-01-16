package software.ulpgc.io;

import software.ulpgc.model.Weapon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWeaponReader implements WeaponReader {
    private final BufferedReader reader;
    private final WeaponDeserializer deserializer;

    public FileWeaponReader(File file, WeaponDeserializer deserializer) throws IOException {
        this.reader = readerOf(file);
        this.deserializer = deserializer;
        SkipFirstLine();
    }

    private BufferedReader readerOf(File file) throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    }


    private void SkipFirstLine() throws IOException {
        reader.readLine();
    }

    @Override
    public Weapon read() throws IOException {
        return deserialize(reader.readLine());
    }

    private Weapon deserialize(String line) {
        return line!=null?deserializer.deserialize(line):null;
    }
}
