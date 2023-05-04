package domain;

public class TerritoryController {
    
    private static TerritoryController instance;
    public static TerritoryController getInstance() {
        if (instance == null) {
            instance = new TerritoryController();
        }
        return instance;
    }



    Territory westEurope = new Territory(null, null);
    Territory eastEurope = new Territory(null, null);
    Territory midEurope = new Territory(null, null);
    Territory turkey = new Territory(null, null);
    Territory scandinavia = new Territory(null, null);
    Territory russia = new Territory(null, null);
    Territory britania = new Territory(null, null);



    Territory india = new Territory(null, null);
    Territory china = new Territory(null, null);
    Territory japan = new Territory(null, null);
    Territory mongolia = new Territory(null, null);
    Territory singapure  = new Territory(null, null);
    Territory iran = new Territory(null, null);
    Territory afghanistan = new Territory(null, null);



    Territory egypt = new Territory(null, null);
    Territory morocco = new Territory(null, null);
    Territory southAfrica = new Territory(null, null);
    Territory madagascar = new Territory(null, null);



    Territory brasil = new Territory(null, null);
    Territory peru = new Territory(null, null);
    Territory argantina = new Territory(null, null);
    Territory venesuela = new Territory(null, null);



    Territory eastCanada = new Territory(null, null);
    Territory westCanada = new Territory(null, null);
    Territory eastUSA = new Territory(null, null);
    Territory westUSA = new Territory(null, null);
    Territory mexico = new Territory(null, null);



    Territory eastAustralia = new Territory(null, null);
    Territory westAustralia = new Territory(null, null);
    Territory newZelland = new Territory(null, null);
    Territory indonesia = new Territory(null, null);
    
    
    public Territory[] getAllTerritories() {
        Territory[] territories = new Territory[]{
            westEurope, eastEurope, midEurope, turkey, scandinavia, russia, britania,
            india, china, japan, mongolia, singapure, iran, afghanistan,
            egypt, morocco, southAfrica, madagascar,
            brasil, peru, argantina, venesuela,
            eastCanada, westCanada, eastUSA, westUSA, mexico,
            eastAustralia, westAustralia, newZelland, indonesia
        };

        return territories;
    }

}
