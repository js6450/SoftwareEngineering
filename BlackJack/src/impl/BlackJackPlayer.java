package impl;

import api.Card;
import api.Hand;
import api.Player;

public class BlackJackPlayer implements Player {

    protected double pMoney;
    protected String pName = "";
    protected BlackJackHand pHand = new BlackJackHand();

    public BlackJackPlayer(String playerName){
        pName = playerName;
        pMoney = 100.0;
    }

    //individual cards when the player calls hit
    public void receive(Card card){
        pHand.addCard(card);
    }

    /*
     * Receive a hand
     */
    public void receive(Hand hand){
        pHand = (BlackJackHand) hand;
    }

    /*
     * Return the current hand to the caller
     */
    public Hand getHand(){
        return pHand;
    }

    /*
     * Place a wager
     */
    public double wager(){
        double wager = 10.0;
        pMoney -= wager;
        return wager;
    }

    /*
     * Give a player money (upon winning a round)
     */
    public void payOut(double money){
        pMoney += money;
    }


    /*
     * Return the amount of money currently available to the player
     */
    public double getMoney(){
        return pMoney;
    }

    /*
     * Return the name of the player
     */
    public String getName(){
        return pName;
    }

    /*
     * Whether the player would like request a card: an affirmative
     * return value indicates the player would; otherwise
     * not. (Essentially a request to 'hit' in Black Jack.)
     */
    public boolean requestCard(){
        if(pHand.valueOf() > 20){
            return false;
        }
        else{
            return false;
        }
    }

	@Override
	public int compareTo(Player o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
