package software.ulpgc.control;

import software.ulpgc.io.*;
import software.ulpgc.model.Weapon;
import software.ulpgc.ui.ImportDialog;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ImportFileCommand implements Command{
    private final ImportDialog dialog;

    public ImportFileCommand(ImportDialog dialog) {
        this.dialog = dialog;
    }


    @Override
    public void execute() throws IOException, SQLException {
        try(WeaponReader reader = new FileWeaponReader(inputFile(),getCsvWeaponDeserializer());
            WeaponWriter writer = DatabaseWeaponWriter.open(outputFile())) {
            doExecute(reader, writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doExecute(WeaponReader reader, WeaponWriter writer) throws IOException, SQLException {
        while (true){
            Weapon weapon = reader.read();
            if (weapon==null)break;
            writer.write(weapon);
        }
    }

    private File outputFile() {
        return new File("weaponfromfile.db");
    }

    private File inputFile() {
        return dialog.get();
    }

    private WeaponDeserializer getCsvWeaponDeserializer() {
        return new CsvWeaponDeserializer();
    }


}
