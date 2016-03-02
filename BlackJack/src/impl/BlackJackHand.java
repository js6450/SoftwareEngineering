package impl;

import java.util.HashSet;
import java.util.Set;

import api.Card;
import api.Hand;

import java.lang.Comparable;

public class BlackJackHand extends Hand {

    protected Set<Card> cards = new HashSet<Card>();
    protected int totalVal = 0;

    public void addCard(Card card) {
        cards.add(card);
    }

    public Set<Card> getCards() {
        return cards;
    }

    public boolean isValid(){
        if(totalVal > 21){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean isWinner(){
    	
    	for(int i = 0; i < cards.size(); i++){
    		
    	}
        if(totalVal == 21){
            return true;
        }
        else{
            return false;
        }
        //consider dealer card
    }

    public int valueOf(){
        for (Card card : cards) {
            totalVal += card.getValue().getValue();
        }
        return totalVal;
    }

    public void collectHand(){
        cards.clear();
    }

	@Override
	public int compareTo(Hand o) {
		// TODO Auto-generated method stub
		
		return 0;
	}
}

