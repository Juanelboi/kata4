package software.ulpgc.model;

public enum WeaponType {
    Type,
    GlintstoneStaff,
    Greatsword,
    Bow,
    ThrustingSword,
    Crossbow,
    ColossalWeapon,
    Greataxe,
    CurvedSword,
    Halberd,
    Flail,
    Axe,
    Warhammer,
    Torch,
    CurvedGreatsword,
    Dagger,
    Claw,
    HeavyThrustingSword,
    Spear,
    StraightSword,
    Fist,
    SacredSeal,
    Hammer,
    LightBow,
    Katana,
    Twinblade,
    Greatbow,
    Whip,
    ColossalSword,
    Reaper,
    Ballista,
    GreatSpear;


    public static WeaponType parseWeaponType(String s) {
        String[] weat = s.split(" ");
        String type = "";
        for (String string : weat) {
            type+=string;
        }
        for (WeaponType value : WeaponType.values()) {
            if (type.equals(value.name())) return value;
        }
        return null;
    }
}
