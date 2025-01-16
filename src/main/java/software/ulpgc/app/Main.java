package software.ulpgc.app;

import software.ulpgc.control.ImportFileCommand;
import software.ulpgc.ui.ImportDialog;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
            new ImportFileCommand(importFileDialog()).execute();

        }


        private static ImportDialog importGzipDialog() {
            return ()->new File("ERW.csv.gz");
        }
        private static ImportDialog importFileDialog() {
            return ()->new File("ERW.csv");
        }private static ImportDialog WriteDialog() {
            return ()->new File("weapons.db");
        }

}