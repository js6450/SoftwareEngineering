package impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import api.Card;
import api.Card.Suit;
import api.Card.Value;
import api.Dealer;
import api.Hand;
import api.Player;

public class BlackJackDealer extends  BlackJackPlayer implements Dealer {
	
	
	//?
	public BlackJackDealer() {
		super("dealer");	// TODO Auto-generated constructor stub
	}

	protected Set<Card> deck = new HashSet<Card>();
    /*
     * Deal a single card to a player
     */
    public void dealCard(Player player){
    	Card newCard = deck.iterator().next();
    	deck.remove(newCard);
        player.receive(newCard);
    }

    /*
     * Deal cards to all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    @SuppressWarnings("null")
	public void dealTable(List<Player> players){

        for(Suit suit: Card.Suit.values()){
            for (Value value: Card.Value.values()) {
                deck.add(new Card(value, suit));
            }
        }

        BlackJackHand dealerHand = new BlackJackHand();
        Card card1 = deck.iterator().next();
        deck.remove(card1);
        Card card2 = deck.iterator().next();
        deck.remove(card2);
        dealerHand.addCard(card1);
        dealerHand.addCard(card2);
        this.receive(dealerHand);

        for (Player player: players) {
                BlackJackHand playerHand = new BlackJackHand();
                Card pCard1 = deck.iterator().next();
                deck.remove(pCard1);
                Card pCard2 = deck.iterator().next();
                deck.remove(pCard2);
                playerHand.addCard(pCard1);
                playerHand.addCard(pCard2);
                player.receive(playerHand);
        }
    }

    /*
     * Collect all cards from a single player.
     */
    public void collectCards(Player player){
        ((BlackJackHand) player.getHand()).collectHand();
    }

    /*
     * Collect cards from all players at the table. Note that in Black
     * Jack, that also means the dealer themself!
     */
    public void collectCards(List<Player> players){
        for (Player player:players) {
            this.collectCards(player);
        }
        deck.clear();
    }

    /*
     * Get the hand of the dealer.
     */
    public Hand getHand(){
        return this.pHand;
    }
}
