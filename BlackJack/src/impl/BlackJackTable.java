package impl;

import java.util.Map;

import api.Dealer;
import api.Player;
import api.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.String;


public class BlackJackTable extends Table{

//    protected List<Player> players = new ArrayList<Player>();
//    protected Dealer dealer;
//    protected Map<Player, Double> wagers = new HashMap<Player,Double>();

    public BlackJackTable(int numberOfPlayers){
    	//initialize dealer
    	this.wagers = new HashMap<Player, Double>();
    	this.players = new ArrayList<Player>();
    	this.dealer = new BlackJackDealer();
        for(int i = 0; i < numberOfPlayers; i++){
            String playerName = "player " + i;
            players.add(new BlackJackPlayer(playerName));
        }
    }

	/*
     * A game is over when there are no players, or no players with
     * money to bet
     */
    public boolean isGameOver() {
    	for(Player player: players){
    		if(player.getMoney() > 0.0){
    			return false;
    		}
    	}
        if(players.isEmpty() == true){
        	System.out.println("No more players left");
            return true;
        }
        System.out.println("All players are out of money");
        return true;

    }


    /*
     * A string representation of the table
     */
    public String toString(){
        String tableStatus = "";
        for (Player player:players) {
            tableStatus += player.getName() + " has " + Double.toString(player.getMoney()) + " left.\n";
        }
        return tableStatus;
    }

    /*
     * A method that brings together actions of a round
     */
    public void round() {
        collectBets();
        dealer.dealTable(players);
        playerTurns();
        playerEvaluations();
        dealer.collectCards(players);
    }

    /*
     * Collect bets from all players at the table
     */
    protected void collectBets(){
        for (Player player : players) {
        	double pWager = player.wager();
            wagers.put(player, pWager);
        }

    }

    /*
     * Give each player a turn. Note that in Black Jack, the dealer
     * should have a turn as well!
     */
    protected void playerTurns(){
        if(dealer.getHand().valueOf() < 17){
            dealer.dealCard((Player) dealer);
        }
        for (Player player:players) {
            if(player.requestCard()) {
                dealer.dealCard(player);
            }
        }
    }

    /*
     * Evaluate each players hand with respect the rules of the game,
     * and to the dealer. If a player has a winning hand, they should
     * be paid based on their respective information in the wager
     * table.
     */
    protected void playerEvaluations(){
        if(!dealer.getHand().isWinner()) {
            int dealerVal = dealer.getHand().valueOf();
            
            List<Player> toBeRemoved = new ArrayList<Player>();
            
            for (Player player : players) {
                if (player.getHand().isWinner()) {
                    player.payOut(wagers.get(player) * 2.0);
                    String status = "Player " + player.getName() + " has won";
                    System.out.println(status);
                }
                if (player.getHand().isValid()) {
                    int playerVal = player.getHand().valueOf();
                    if (playerVal > dealerVal) {
                        player.payOut(wagers.get(player) * 2.0);
                        String status = "Player " + player.getName() + " has won";
                        System.out.println(status);
                    }
                }
                if(player.getMoney() <= 0.0){
                	toBeRemoved.add(player);              
                }

            }
            
            for(int i = 0; i < toBeRemoved.size(); i++){
            	players.remove(toBeRemoved.remove(i));
            }
        
        }
    }

}