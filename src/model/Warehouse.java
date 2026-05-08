package model;

public class Warehouse {

    private final String address;
    private final double m2;
    private final Cask[] locations;
    private boolean isFull;

    public Warehouse(String address, double m2, int storageSpaces) {
        this.address = address;
        this.m2 = m2;
        locations = new Cask[storageSpaces];
        isFull = false;
    }

    private boolean isCaskInWareHouse(Cask cask) {
        for (Cask value : locations) {
            if (value == cask) {
                return true;
            }
        }
        return false;
    }

    public void addCask(Cask cask) {
        if (cask == null) {
            throw new IllegalArgumentException("Cask cannot be null");
        }
        // Checks if cask is already in warehouse
        if (isCaskInWareHouse(cask)) {
            throw new IllegalArgumentException("Cask is already in warehouse");
        }

        // Checks if there is room for the cask, if yes; set lokation to cask and quit
        for (int i = 0; i < locations.length; i++) {
            if (locations[i] == null) {
                locations[i] = cask;
                return;
            }
        }

        // If we get here there is no more room in the warehouse
        isFull = true;
        throw new IllegalStateException("There is no more room in the warehouse");
    }

    public void removeCask(Cask cask) {
        if (cask == null) {
            throw new IllegalArgumentException("Cask cannot be null");
        }
        
        for (int i = 0; i < locations.length; i++) {
            if (locations[i] == cask) {
                locations[i] = null;
                isFull = false;
                return;
            }
        }
    }
    
    public boolean isFull() {
        return isFull;
    }
    
    @Override
    public String toString() {
        return "Warehouse: " + address + ", m2: " + m2;
    }

}
