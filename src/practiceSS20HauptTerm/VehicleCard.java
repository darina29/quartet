package practiceSS20HauptTerm;

import java.util.HashMap;
import java.util.Map;

public class VehicleCard implements Valuable,SpeedCheck,RacingScore, Comparable<VehicleCard> {
    public enum Category {
        PRICE_EUR("Preis",1),
        CYLINDER_CAPACITY_CM3("Hubraum", 5),
        ENGINE_POWER_HP("Leistung", 4),
        ACCELERATION_SEC("Beschleunigung", 3),
        VELOCITY_KMH("Geschwindigkeit", 2),
        CONSUMPTION_L("Verbrauch", 0);

        //values:
        //PRICE EUR, CYLINDER CAPACITY CM3, ENGINE POWER HP, ACCELERATION SEC, VELOCITY KMH, CONSUMPTION L;
        // (”Preis”,1), (”Hubraum”,5), (”Leistung”,4), (”Beschleunigung”,3), (”Geschwindigkeit”,2), (”Verbrauch”,0)
        final private String categoryName;
        final private int factor;

        Category(String categoryName, int factor) {
            /*throws IllegalArgumentException if categoryName null or empty or if factor less than 0 */
            if(categoryName == null || categoryName.isEmpty() || factor < 0) {
                throw new IllegalArgumentException("categoryName is null or empty or factor is less than 0");
            }
            this.categoryName = categoryName;
            this.factor = factor;
        }

        public int bonus(Double value) {
            if(categoryName.equals("Verbrauch")) {
                return (int)(this.factor + value);
            }

            return (int)(this.factor * value);
            /* return int(factor times value) */
            // must be overriden for CONSUMPTION L. returns int(value + factor).
        }
        public int getFactor() {
            return this.factor;
        }

        @Override
        public String toString() {
            return this.categoryName;
        }
////////////////////////
        String getCategoryName() {
            return categoryName;
        }

    }

    private String name;
    private Map<Category, Double> categories;

    public VehicleCard(String name, Map<Category, Double> categories) {
        boolean price = false;
        boolean cylinder = false;
        boolean engine = false;
        boolean acceleration = false;
        boolean velocity = false;
        boolean consumption = false;

        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is null or empty");
        }

        if(categories == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is null or empty");
        }

        for (Map.Entry<Category, Double> entry : categories.entrySet()) {
            if(entry.getKey() == null || entry.getValue() == null || entry.getValue() < 0) {
                throw new IllegalArgumentException("Key or Value in categories Map is null or value < 0");
            }
            if(entry.getKey() == Category.ACCELERATION_SEC) {
                acceleration = true;
            }
            if(entry.getKey() == Category.CONSUMPTION_L) {
                consumption = true;
            }
            if(entry.getKey() == Category.CYLINDER_CAPACITY_CM3) {
                cylinder = true;
            }
            if(entry.getKey() == Category.VELOCITY_KMH) {
                velocity = true;
            }
            if(entry.getKey() == Category.PRICE_EUR) {
                price = true;
            }
            if(entry.getKey() == Category.ENGINE_POWER_HP) {
                engine = true;
            }
        }

        if(acceleration == false || consumption == false || cylinder == false ||
                velocity == false || price == false || engine == false) {
            throw new IllegalArgumentException("not every Category exists in categories");
        }

        this.name = name;
        this.categories = categories;

        // throws IllegalArgumentException if name is null or empty.
        // throws IllegalArgumentException if categories is null or not every Category exists in categories.
        // throws IllegalArgumentException if categories contains any null value or values less than 0.
        // set member variables
    }


    @Override
    public int compareTo(VehicleCard other) {   //bez final ...
        // compare by totalBonus
        if(this.totalBonus() < other.totalBonus()) {
            return -1;
        } else if (this.totalBonus() == other.totalBonus()) {
            return 0;
        } else {
            return 1;
        }
    }

    protected int getBonus(Category category) {
        return category.bonus(categories.get(category));
    }


    public int totalBonus() {
        int sum = 0;
        for (Map.Entry<Category, Double> entry : categories.entrySet()) {
            sum += getBonus(entry.getKey());
        }
        // return total Bonus of card.
        return sum;
    }
/////
    protected String categoryToString(Category category) {
        return category.toString();
    }
////
    public static Map<Category, Double> newCategoriesMap(double price, double capa, double pwr, double acc, double velo, double cons) {
        Map<Category, Double> map = new HashMap<>();
        map.put(Category.PRICE_EUR, price);
        map.put(Category.CYLINDER_CAPACITY_CM3, capa);
        map.put(Category.ENGINE_POWER_HP, pwr);
        map.put(Category.ACCELERATION_SEC, acc);
        map.put(Category.VELOCITY_KMH, velo);
        map.put(Category.CONSUMPTION_L, cons);

        return map;
    }
//ws19
    @Override
    public int hashCode() {
        /*hash ”name”(hint:Objects−class)*/
        return (int) name.hashCode();
    }
//ws19
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == VehicleCard.class && this.name.equals(((VehicleCard) obj).getName()) && this.totalBonus() == ((VehicleCard) obj).totalBonus();
        /*true if obj is instance of VehicleCard and name and totalBonus match, false otherwise*/
    }
//v ss20 novoe usloviye : use categoryToString for representation of Category values
    @Override
    public String toString() {

        /* ”− <name>(totalBonus) −> {<categories>}” e.g.:
        − Audi TT RS Roadster(73032) −> {Preis=58650.0, Hubraum=2480.0, Leistung=350.0, Beschleunigung=4.6, Geschwindigkeit=280.0, Verbrauch=9.2}*/
        StringBuilder string = new StringBuilder();

        int i = 0;

        for (Map.Entry<Category, Double> entry : this.getCategories().entrySet()) {
            i++;
            string.append(categoryToString(entry.getKey())).append("=").append(entry.getValue().toString());
            if (i < this.getCategories().entrySet().size()) {
                string.append(", ");
            }
        }

        return "- " + name + "(" + totalBonus() + ") -> {" + string + "}";
    }

    public String getName() {
        return name;
    }

    public Map<Category, Double> getCategories() {
        return categories;
    }


	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		
		double sum=0;
		for(Map.Entry<Category, Double>entry : categories.entrySet()){
			sum=sum+entry.getValue();
		}
		return sum;
	}


	@Override
	public boolean tooFast(double limit) {
		// TODO Auto-generated method stub
		double toleranz= limit+(limit*5/100);
		if( (this.getCategories().get(Category.VELOCITY_KMH)) > toleranz) {
			return true;
		}
		return false;
	}


	@Override
	public double calculateScore() {
		// TODO Auto-generated method stub
		double res= this.getCategories().get(Category.ENGINE_POWER_HP) - this.getCategories().get(Category.VELOCITY_KMH)*9;
		return res;
	}




	
}
