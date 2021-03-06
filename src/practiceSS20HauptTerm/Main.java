package practiceSS20HauptTerm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import practiceSS20HauptTerm.VehicleCard.Category;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ss20
		//haupt termin task8
		task8();
		task8nt();
		task9();
		task9nt();
	}
		private static void task8() {
			
			VehicleCard peu = new VehicleCard("Peugeot Rifter", VehicleCard.newCategoriesMap(22600, 1899, 186, 7.9, 205, 8.5));
			System.out.println(peu.getValue());
			VehicleCard vw  = new VehicleCard("VW Golf", VehicleCard.newCategoriesMap(15400, 1700, 160, 4.7, 220, 5.7));
			System.out.println(vw.getValue());
			VehicleCard bmw = new FoilVehicleCard("BMW G11", VehicleCard.newCategoriesMap(120000, 2500, 200, 6.8, 250, 9.4),Arrays.asList(VehicleCard.Category.CONSUMPTION_L));
			System.out.println(bmw.getValue());
			VehicleCard ast = new FoilVehicleCard("Aston Martin DB5", VehicleCard.newCategoriesMap(590000, 3995, 286, 7.1, 229, 10.4),Arrays.asList(VehicleCard.Category.PRICE_EUR,VehicleCard.Category.ENGINE_POWER_HP,VehicleCard.Category.VELOCITY_KMH));
			System.out.println(ast.getValue());
			
			
			/* Erwarteter Output
			 * 24906.4
			 * 17490.4
			 * 17566.6
			 * 37157.96875
			 */
			
		}
		
		private static void task8nt() {
			
			VehicleCard peu = new VehicleCard("Peugeot Rifter", VehicleCard.newCategoriesMap(22600, 1899, 186, 7.9, 100, 8.5));
			System.out.println(peu.tooFast(100));
			System.out.println(peu.tooFast(95));
			System.out.println(peu.tooFast(96));
			
			VehicleCard bmw = new FoilVehicleCard("BMW G11", VehicleCard.newCategoriesMap(120000, 2500, 200, 6.8, 100, 9.4),Arrays.asList(VehicleCard.Category.CONSUMPTION_L));
			System.out.println(bmw.tooFast(100));
			System.out.println(bmw.tooFast(90));
			System.out.println(bmw.tooFast(91));
			System.out.println(bmw.tooFast(95));
			
			
			/* Erwarteter Output
			* false
			* true
			* false
			* false
			* true
			* false
			* false
			*/
		}
		private static void task9() {
			
			List<VehicleCard> l = new ArrayList<>(Arrays.asList(new VehicleCard("Peugeot Rifter", VehicleCard.newCategoriesMap(22600, 1899, 186, 7.9, 205, 8.5)),
					new VehicleCard("VW Golf", VehicleCard.newCategoriesMap(15400, 1700, 160, 4.7, 220, 5.7)),
					new FoilVehicleCard("BMW G11", VehicleCard.newCategoriesMap(120000, 2500, 200, 6.8, 250, 9.4),Arrays.asList(VehicleCard.Category.CONSUMPTION_L)),
					new FoilVehicleCard("Aston Martin DB5", VehicleCard.newCategoriesMap(590000, 3995, 286, 7.1, 229, 10.4),Arrays.asList(VehicleCard.Category.PRICE_EUR,VehicleCard.Category.ENGINE_POWER_HP,VehicleCard.Category.VELOCITY_KMH))
				));
			List<VehicleCard> test = new ArrayList<>(l);
		    List<Integer> vals = new ArrayList<>(Arrays.asList(2000000,1000,30000,1000000));
		    for (Integer v : vals) {
		    	test = new ArrayList<>(l);
	            select(test, v);		
			    System.out.println(test);
		    }
		    
			
			/* Erwarteter Output
			 * [- VW Golf(24999) -> {Preis=15400.0, Hubraum=1700.0, Geschwindigkeit=220.0, Verbrauch=5.7, Leistung=160.0, Beschleunigung 0=4.7, - Peugeot Rifter(33280) -> {Preis=22600.0, Hubraum=1899.0, Geschwindigkeit=205.0, Verbrauch=8.5, Leistung=186.0, Beschleunigung 1=7.9, - BMW G11(133838) -> {Preis=120000.0, Hubraum=2500.0, Geschwindigkeit=250.0, *Verbrauch*=9.4, Leistung=200.0, Beschleunigung 2=6.8, - Aston Martin DB5(1203210) -> {*Preis*=590000.0, Hubraum=3995.0, *Geschwindigkeit*=229.0, Verbrauch=10.4, *Leistung*=286.0, Beschleunigung 3=7.1]
			 * []
			 * [- VW Golf(24999) -> {Preis=15400.0, Hubraum=1700.0, Geschwindigkeit=220.0, Verbrauch=5.7, Leistung=160.0, Beschleunigung 4=4.7]
			 * [- VW Golf(24999) -> {Preis=15400.0, Hubraum=1700.0, Geschwindigkeit=220.0, Verbrauch=5.7, Leistung=160.0, Beschleunigung 5=4.7, - Peugeot Rifter(33280) -> {Preis=22600.0, Hubraum=1899.0, Geschwindigkeit=205.0, Verbrauch=8.5, Leistung=186.0, Beschleunigung 6=7.9, - BMW G11(133838) -> {Preis=120000.0, Hubraum=2500.0, Geschwindigkeit=250.0, *Verbrauch*=9.4, Leistung=200.0, Beschleunigung 7=6.8]
			 */	    
		}
		
		private static void select(List<VehicleCard> l, Integer limit) {
			// TODO Auto-generated method stub
			List<VehicleCard>myList = new ArrayList<>();
			for(VehicleCard v: l) {
				if(v.totalBonus()<limit) {
					myList.add(v);
				}
			}
			l.clear();
			l.addAll(myList);
			
			Collections.sort(l, new Comparator<VehicleCard>() {
				
				@Override
				public int compare(VehicleCard v1, VehicleCard v2) {
					return v2.getName().compareTo(v1.getName());
				}
			});
			
		}
		
		
		private static void task9nt() {
			
			List<VehicleCard> l = new ArrayList<>(Arrays.asList(new VehicleCard("Peugeot Rifter", VehicleCard.newCategoriesMap(22600, 1899, 186, 7.9, 205, 8.5)),
					new VehicleCard("VW Golf", VehicleCard.newCategoriesMap(15400, 1700, 160, 4.7, 220, 5.7)),
					new FoilVehicleCard("BMW G11", VehicleCard.newCategoriesMap(120000, 2500, 200, 6.8, 250, 9.4),Arrays.asList(VehicleCard.Category.CONSUMPTION_L)),
					new FoilVehicleCard("Aston Martin DB5", VehicleCard.newCategoriesMap(590000, 3995, 286, 7.1, 229, 10.4),Arrays.asList(VehicleCard.Category.PRICE_EUR,VehicleCard.Category.ENGINE_POWER_HP,VehicleCard.Category.VELOCITY_KMH))
				));
			List<VehicleCard> test = new ArrayList<>(l);
	        replaceConsumption(test);
	        test.forEach(System.out::println);
			
			
			/* Erwarteter Output
			*  (Wenn task7 schon gelöst wurde, sieht die Ausgabe der Kategorien etwas anders aus)
			* - BMW G11(133838) -> {Preis=120000.0, Hubraum=2500.0, Leistung=200.0, Beschleunigung=6.8, Geschwindigkeit=250.0, *Verbrauch*=9.4}
			* - Peugeot Rifter(33280) -> {Preis=22600.0, Hubraum=1899.0, Leistung=186.0, Beschleunigung=7.9, Geschwindigkeit=205.0, Verbrauch=8.5}
			* - VW Golf(24999) -> {Preis=15400.0, Hubraum=1700.0, Leistung=160.0, Beschleunigung=4.7, Geschwindigkeit=220.0, Verbrauch=5.7}
			* - VW Golf(24999) -> {Preis=15400.0, Hubraum=1700.0, Leistung=160.0, Beschleunigung=4.7, Geschwindigkeit=220.0, Verbrauch=5.7}
			*/
		}

		public static void replaceConsumption(List<VehicleCard> l) {
		VehicleCard maxCard = Collections.max(l, new Comparator<VehicleCard>() {
				
		@Override
		public int compare(VehicleCard o1, VehicleCard o2) {
			// TODO Auto-generated method stub
			if(o2.getCategories().get(Category.CONSUMPTION_L) > o1.getCategories().get(Category.CONSUMPTION_L)) {
			return 1;
			}else if(o2.getCategories().get(Category.CONSUMPTION_L) == o1.getCategories().get(Category.CONSUMPTION_L)){
				return 0;
			}else return -1;
			
		}
			});
			
		VehicleCard minCard = Collections.min(l, new Comparator<VehicleCard>() {
				
				@Override
				public int compare(VehicleCard o1, VehicleCard o2) {
					// TODO Auto-generated method stub
					if(o2.totalBonus() > o1.totalBonus()) {
						return 1;
						}else if(o2.totalBonus() == o1.totalBonus()){
							return 0;
						}else return -1;
				}
			});
			
		// for (VehicleCard vehicleCard : l) {
		//	 if(vehicleCard == maxCard) {
		//		 vehicleCard=minCard;
		//	 }
		//}

		
		for (int i = 0; i < l.size(); i++) {
			if(l.get(i) == maxCard) {
				l.set(i, minCard);
			}
		}
		
		Collections.sort(l, new Comparator<VehicleCard>() {
			@Override
			public int compare(VehicleCard o1, VehicleCard o2) {
				if(o2.getCategories().get(Category.CONSUMPTION_L)> o1.getCategories().get(Category.CONSUMPTION_L)) {
					return 1;
				}else if(o2.getCategories().get(Category.CONSUMPTION_L)== o1.getCategories().get(Category.CONSUMPTION_L)) {
					return 0;
				}else {
					return -1;
				}
			}
		}); 
	
			
	}
		
		

	}


