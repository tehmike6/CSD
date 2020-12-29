package csd.uoc.gr.A23;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class GreatHouse {
    final String name;
    final String sigil;
    final String words;
    final String lord;
    final List<Soldier> army = new ArrayList<Soldier>();

    public GreatHouse(@NotNull String name,@NotNull String sigil,@NotNull String words,@NotNull String lord) {
        this.name = name;
        this.sigil = sigil;
        this.words = words;
        this.lord = lord;
    }

    public void addSoldier(Soldier soldier){
        if(soldier == null)
            throw new RuntimeException("Soldier is NULL");
        army.add(soldier);
    }

    public Soldier getSoldier(){
        for(int i=0;i<army.size();i++){
            if(!army.get(i).isDefeated()){
                return army.get(i);
            }
        }
        throw new RuntimeException("No Soldier is left alive in army");
    }
    public boolean isDefeated(){
        for(int i=0;i<army.size();i++){
            if(!army.get(i).isDefeated()){
                return false;
            }
        }
        return true;
    }
    public String toString(){
        return "The GreatHouse of "+name+" has lord "+ lord+". Their sigil is a '"+sigil+"' and their words are'"+words+"'.";
    }
}
