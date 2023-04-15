package domain;

public class TerritoryCard {
    private String territoryName;
    private String imagePath;
    private TroopType troopType;

    public TerritoryCard(String territoryName, String imagePath, TroopType troopType) {
        this.territoryName = territoryName;
        this.imagePath = imagePath;
        this.troopType = troopType;
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(String territoryName) {
        this.territoryName = territoryName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public TroopType getTroopType() {
        return troopType;
    }

    public void setTroopType(TroopType troopType) {
        this.troopType = troopType;
    }

    public enum TroopType {
        INFANTRY, CAVALRY, ARTILLERY
    }
}

