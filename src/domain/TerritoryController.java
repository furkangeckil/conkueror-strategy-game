package domain;

public class TerritoryController {



    Territory westEurope = new Territory("WE", null, null);
    Territory eastEurope = new Territory("EE",null, null);
    Territory midEurope = new Territory("ME",null, null);
    Territory turkey = new Territory("T",null, null);
    Territory scandinavia = new Territory("S",null, null);
    Territory russia = new Territory("R",null, null);
    Territory britain = new Territory("B",null, null);

    Territory india = new Territory("I",null, null);
    Territory china = new Territory("C",null, null);
    Territory japan = new Territory("J",null, null);
    Territory mongolia = new Territory("M",null, null);
    Territory singapure  = new Territory("Si",null, null);
    Territory iran = new Territory("Ir",null, null);
    Territory afghanistan = new Territory("A",null, null);



    Territory egypt = new Territory("E",null, null);
    Territory morocco = new Territory("M",null, null);
    Territory southAfrica = new Territory("SA",null, null);
    Territory madagascar = new Territory("Ma",null, null);



    Territory brasil = new Territory("Br",null, null);
    Territory peru = new Territory("P",null, null);
    Territory argantina = new Territory("Ar",null, null);
    Territory venesuela = new Territory("V",null, null);



    Territory eastCanada = new Territory("EC",null, null);
    Territory westCanada = new Territory("WC",null, null);
    Territory eastUSA = new Territory("EU",null, null);
    Territory westUSA = new Territory("WU",null, null);
    Territory mexico = new Territory("Me",null, null);



    Territory eastAustralia = new Territory("EA",null, null);
    Territory westAustralia = new Territory("WA",null, null);
    Territory newZelland = new Territory("NZ",null, null);

    
    
    public Territory[] getAllTerritories() {
        Territory[] territories = new Territory[]{
            westEurope, eastEurope, midEurope, turkey, scandinavia, russia, britain,
            india, china, japan, mongolia, singapure, iran, afghanistan,
            egypt, morocco, southAfrica, madagascar,
            brasil, peru, argantina, venesuela,
            eastCanada, westCanada, eastUSA, westUSA, mexico,
            eastAustralia, westAustralia, newZelland
        };

        return territories;
    }

}