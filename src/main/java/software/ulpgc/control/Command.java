package software.ulpgc.control;

import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    void execute() throws IOException, SQLException;
}
