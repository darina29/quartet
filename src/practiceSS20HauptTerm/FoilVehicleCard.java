package practiceSS20HauptTerm;

import java.util.List;
import java.util.Map;

import practiceSS20HauptTerm.VehicleCard.Category;

public class FoilVehicleCard extends VehicleCard {
    private List<Category> specials;
// norm no bez final
    public FoilVehicleCard(String name, Map<Category, Double> categories, List<Category> specials) {
        // throws IllegalArgumentException if specials contains more than 3 items or is null
        // set member variables
        super(name, categories);

        if(specials == null || specials.size() > 3) {
            throw new IllegalArgumentException("specials contains more than 3 items or is null");
        }
        this.specials = specials;
    }

//////////ws19///////////////////
    @Override
    public int totalBonus() {
        // return total Bonus of card.
        // Special Categories bonus points are doubled.
        int sum = 0;

        for(Map.Entry<Category, Double> entry : getCategories().entrySet()) {
            if(specials.contains(entry.getKey())) {
                sum += entry.getValue() * 2;
            }
            else {
                sum += entry.getValue();
            }
        }
        return sum;
    }

    @Override
    protected int getBonus(Category category) {
        if (specials.contains(category)) {
            return 2 * super.getBonus(category);
        }
        return super.getBonus(category);
    }

    @Override
    protected String categoryToString(Category category) {
        return "*" + super.categoryToString(category) + "*";
    }


//////////////////////////////////////////////////////////toString     
    @Override
    public String toString() {
        {

            String string = "";

            int i = 0;

            for (Map.Entry<Category, Double> entry : this.getCategories().entrySet()) {
                i++;
                string += entry.getKey().getCategoryName() + "=" + entry.getValue().toString();
                if (i < this.getCategories().entrySet().size()) {
                    string += ", ";
                }
            }

            return "- " + super.getName() + "(" + totalBonus() + ") -> {" + string + "}";
        }
    }
    
    @Override
	public double getValue() {
    	double sum=super.getValue();
		// TODO Auto-generated method stub
		return sum/super.getName().length();
	}
    
	@Override
	public boolean tooFast(double limit) {
		boolean help = true;
		double proz = limit+((limit*10)/100);
		if(this.getCategories().get(Category.VELOCITY_KMH)>proz) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public double calculateScore() {
		// TODO Auto-generated method stub
//		double result;
//		if(super.getCategories().containsKey(Category.ENGINE_POWER_HP)) {
//			return super.getValue()*10;
//		}
		///have done it without for loop, compared to last sem. solution????
		if(this.specials.contains(Category.ENGINE_POWER_HP)) {
			return super.getValue()*2;
		}
		return 0;
	}
	
}
