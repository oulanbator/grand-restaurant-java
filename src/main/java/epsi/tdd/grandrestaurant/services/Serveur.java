package epsi.tdd.grandrestaurant.services;

import java.util.function.BooleanSupplier;

import com.fasterxml.jackson.databind.node.BooleanNode;

public class Serveur {
    private boolean maitreHotel;

    public boolean isMaitreHotel() {
        return maitreHotel;
    }

    public void setMaitreHotel(boolean b) {
        this.maitreHotel = b; 
    }
    
}
