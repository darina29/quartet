package practiceSS20HauptTerm;

import java.util.*;

public class Player implements Comparable<Player> {
    private String name;
    private Queue<VehicleCard> deck = new ArrayDeque<>();

    public Player(final String name) {
        // throw IllegalArgumentException if name is null or empty

        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is null or empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCards(final Collection<VehicleCard> cards) {
        //add cards to end

        deck.addAll(cards);
    }

    public void addCard(final VehicleCard card) {
        deck.add(card);
    }

    public void clearDeck() {
        deck.clear();
    }
//////////////ws19/////////
    private VehicleCard peekNextCard() {
        /*peek next card*/
        return deck.peek();
    }

    public VehicleCard playNextCard() {
        /*poll next card from deck*/
        return deck.poll();
    }


    public int totalBonus(){
        Queue<VehicleCard> tempDeck = new ArrayDeque<>(deck);
        int sum = 0;
        while (!tempDeck.isEmpty()) {
            VehicleCard vehicleCard = tempDeck.poll();
            sum += vehicleCard.totalBonus();
        }

        return sum;
    }

    public int compareTo(Player other) {
        // compare by name[case insensitive]
        int l1 = this.getName().length();
        int l2 = other.getName().length();
        int lmin = Math.min(l1, l2);

        String str1 = this.getName().toLowerCase();
        String str2 = other.getName().toLowerCase();

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        if (l1 != l2) {
            return l1 - l2;
        }

        else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean challengePlayer(Player p) {
        if(p == null || p.equals(this)) {
            throw new IllegalArgumentException("p is null or p is this");
        }


        if(this.peekNextCard() == null || p.peekNextCard() == null) {
            return false;
        }

        VehicleCard thisNextCard = playNextCard();
        VehicleCard pNextCard = p.playNextCard();


        Queue<VehicleCard> cardsOfThis = new ArrayDeque<>();
        Queue<VehicleCard> cardsOfP = new ArrayDeque<>();

        cardsOfThis.add(thisNextCard);
        cardsOfP.add(pNextCard);

        if(thisNextCard.compareTo(pNextCard) == 0) {
            while (this.peekNextCard() != null && p.peekNextCard() != null) {
                thisNextCard = playNextCard();
                pNextCard = p.playNextCard();
                cardsOfThis.add(thisNextCard);
                cardsOfP.add(pNextCard);
                if(thisNextCard.compareTo(pNextCard) == 1) {
                    this.addCards(cardsOfThis);
                    this.addCards(cardsOfP);
                    return true;
                } else if(thisNextCard.compareTo(pNextCard) == -1) {
                    this.addCards(cardsOfThis);
                    this.addCards(cardsOfP);
                    return false;
                } else {
                    continue;
                }

            }
            this.addCards(cardsOfThis);
            p.addCards(cardsOfP);

            return false;
        } else if(thisNextCard.compareTo(pNextCard) == 1) {
            this.addCards(cardsOfThis);
            this.addCards(cardsOfP);

            return true;
        } else {    // == -1
            p.addCards(cardsOfThis);
            p.addCards(cardsOfP);

            return false;
        }
    }



//norm
    public static Comparator<Player> compareByBonus() {
        return new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return p1.deck.element().totalBonus() - p2.deck.element().totalBonus();
            }
        };
    }
//norm
    public static Comparator<Player> compareByDeckSize() {
        return new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                if(p1.deck.size() < p2.deck.size()) {
                    return -1;
                } else if (p1.deck.size() > p2.deck.size()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }
//norm
    @Override
    public String toString() {
        StringBuilder lastPart = new StringBuilder();

        int sum = 0;

        Queue<VehicleCard> deck1 = new ArrayDeque<>();
        for (VehicleCard vehicleCard : deck) {
            sum += vehicleCard.totalBonus();
        }

        for (VehicleCard vehicleCard : deck) {
            lastPart.append(vehicleCard.toString()).append("\n");
        }

        return this.name + "(" + sum + ")" + ":\n" + lastPart.toString();
        /*contains: Player.name (totalBonus), one card per line, e.g.:
        Maria(73214):
        − Porsche 911(73054) −> {Preis=<val> Hubraum=<val> ...}
        − Renault Clio(160)−> {...} */
    }

}