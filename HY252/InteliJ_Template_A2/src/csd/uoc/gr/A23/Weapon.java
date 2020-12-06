package csd.uoc.gr.A23;

public abstract class Weapon {
    final int power;
    private WeaponCarrier holder;

    Weapon(int power){
        this.power = power;
    }

    public void setHolder(WeaponCarrier holder) {
        this.holder = holder;
    }

    public WeaponCarrier getHolder() {
        return holder;
    }

    public int getPower() {
        return power;
    }

    @Override
    public abstract String toString();
}
