package software.ulpgc.io;

import software.ulpgc.model.Scaling;
import software.ulpgc.model.Weapon;
import software.ulpgc.model.WeaponType;

public class CsvWeaponDeserializer implements WeaponDeserializer{
    @Override
    public Weapon deserialize(String line) {
        return deserialize(line.split(";"));
    }

    private Weapon deserialize(String[] field){

        return new Weapon(
                field[0],
                toWeaponType(field[1]),
                toDouble(field[2]),
                toDouble(field[3]),
                toDouble(field[4]),
                toDouble(field[5]),
                toDouble(field[6]),
                toScaling(field[8]),
                toScaling(field[9]),
                toScaling(field[10]),
                toScaling(field[11]),
                toScaling(field[12])
        );
    }

    private Scaling toScaling(String s) {
        return Scaling.parseScaling(s);
    }

    private WeaponType toWeaponType(String s) {
        return WeaponType.parseWeaponType(s);
    }

    private double toDouble(String s) {
        if (s.equals("-")) return 0;
        return Double.parseDouble(s);
    }
}
