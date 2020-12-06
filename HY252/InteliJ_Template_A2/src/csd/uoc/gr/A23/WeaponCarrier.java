package csd.uoc.gr.A23;

public abstract class WeaponCarrier {

    protected Weapon weapon;

    public WeaponCarrier() {
        this.weapon = null;
    }
    public Weapon getWeapon(){
        return weapon;
    }
    public abstract void setWeapon(Weapon weapon);

    public boolean hasWeapon(){
        return weapon != null;
    }

}
