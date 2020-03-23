/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.Arrays;

/**
 * The PokerHand class to identify the type of poker
 * @author votha
 */
public class PokerHand {
    /**
     * enum variable for certains hand type
     */
    public enum HandType{
        HIGH_CARD,PAIR,TWO_PAIR,THREE_OF_KIND,STRAIGHT,FLUSH,FULL_HOUSE,
        FOUR_OF_KIND,STRAIGHT_FLUSH,ROYAL_FLUSH
    }
    /**
     * HandType method to call other method
     * @param cards the array of five cards 
     * @return the HandType corresponding to the cards
     */
    public static HandType classifyHand(Card[] cards){
        HandType poker=HandType.HIGH_CARD;
        if(isPair(cards)==true){
            poker=HandType.PAIR;
        }
        if(isTwoPair(cards)==true){
            poker=HandType.TWO_PAIR;
        }
        if(isThreeKind(cards)==true){
            poker=HandType.THREE_OF_KIND;
        }
        if(isStraight(cards)==true){
            poker=HandType.STRAIGHT;
        }
        if(isFlush(cards)==true){
            poker=HandType.FLUSH;
        }
        if(isFullHouse(cards)==true){
            poker=HandType.FULL_HOUSE;
        }
        if(isFourKind(cards)==true){
            poker=HandType.FOUR_OF_KIND;
        }
        if(isStraightFlush(cards)==true){
            poker=HandType.STRAIGHT_FLUSH;
        }
        if(isRoyalFlush(cards)==true){
            poker=HandType.ROYAL_FLUSH;
        }
        return poker;
}
    
    /**
     * the isPair method to identify any pair in 5 cards
     * @param cards the array of 5 cards
     * @return true if there is a pair and false if there is not
     */
    public static boolean isPair(Card[] cards){
        for(int i=0;i<cards.length;i++){
            for(int j=i+1;j<cards.length;j++){
                if(cards[i].getCardKind()==cards[j].getCardKind()){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * the isTwoPair method to identify two pairs in 5 cards
     * @param cards the array of 5 cards
     * @return true if there are pairs and false if there are not
     */
    public static boolean isTwoPair(Card[] cards){
        if(isPair(cards)==true){
            if(isFourKind(cards)==false){
                if(isPair(Arrays.copyOfRange(cards, 0, 2))==true&&
                        isPair(Arrays.copyOfRange(cards, 2, 4))==true)
                    return true;
                if(isPair(Arrays.copyOfRange(cards, 0, 2))==true&&
                        isPair(Arrays.copyOfRange(cards, 3, 5))==true){
                    return true;
                }
                if(isPair(Arrays.copyOfRange(cards, 1, 3))==true&&
                        isPair(Arrays.copyOfRange(cards, 3, 5))==true){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * the isThreeKind method to identify 3 cards with the same kind in 5 cards
     * @param cards the array of 5 cards
     * @return true if there are and false if there are not
     */
    public static boolean isThreeKind(Card[] cards){
        if(isPair(cards)==true){
        for(int i=0;i<cards.length;i++){
            for(int j=i+1;j<cards.length;j++){
                for(int k=j+1;k<cards.length;k++){
                    if(cards[i].getCardKind()==cards[j].getCardKind()&&
                            cards[i].getCardKind()==cards[k].getCardKind()){
                        return true;
                    }
                }
            }
    }
        }
        return false;
    }
    /**
     * the isFullHouse method to identify a Full House from 5 cards
     * @param cards the array of 5 cards
     * @return true if there is and false if there is not
     */
    public static boolean isFullHouse(Card[] cards){
        if(isThreeKind(Arrays.copyOfRange(cards, 0, 3))==true
                &&isPair(Arrays.copyOfRange(cards, 3, 5))==true){
            return true;
        }
        if(isThreeKind(Arrays.copyOfRange(cards, 3, 5))==true
                &&isPair(Arrays.copyOfRange(cards, 0, 3))==true){
            return true;
        }
         return false;
    }
    /**
     * the isFourKind method to identify 4 cards with the same kind  in 5 cards
     * @param cards the array of 5 cards
     * @return true if there are and false if there are not
     */
    public static boolean isFourKind(Card[] cards){
        if(isPair(cards)==true){
        for(int i=0;i<cards.length;i++){
            for(int j=i+1;j<cards.length;j++){
                for(int k=j+1;k<cards.length;k++){
                    for(int x=k+1;x<cards.length;x++){
                        if(cards[i].getCardKind()==cards[j].getCardKind()&&
                                cards[i].getCardKind()==cards[k].getCardKind()&&
                                cards[i].getCardKind()==cards[x].getCardKind()){
                            return true;
                    }
                    }
                }
            }
        }
        }
        return false;
    }
    /**
     * the isStraight method to identify if 5 cards are in ascending order 
     * @param cards the array of 5 cards
     * @return true if there are and false if there are not
     */
    public static boolean isStraight(Card[] cards){
        if(isPair(cards)==false){
        if((int)cards[0].getCardKind().ordinal()==(int)cards[1].getCardKind().ordinal()-1&&
                (int)cards[1].getCardKind().ordinal()==(int)cards[2].getCardKind().ordinal()-1&&
                (int)cards[2].getCardKind().ordinal()==(int)cards[3].getCardKind().ordinal()-1&&
                (int)cards[3].getCardKind().ordinal()==(int)cards[4].getCardKind().ordinal()-1){
                    return true;
           }
        }
        return false; 
    }
    /**
     * the isFlush method to identify 5 cards have the same suit
     * @param cards the array of 5 cards
     * @return true if there are and false if there are not
     */
    public static boolean isFlush(Card[] cards){
        if(cards[0].getCardSuit()==cards[1].getCardSuit()&&
                    cards[1].getCardSuit()==cards[2].getCardSuit()&&
                    cards[2].getCardSuit()==cards[3].getCardSuit()&&
                    cards[3].getCardSuit()==cards[4].getCardSuit()){
            return true;
        }
        return false;
    }
    /**
     * the isRoyalFlush method to identify if the 5 cards are royal flush
     * @param cards the array of 5 cards
     * @return true if there are and false if there are not
     */
    public static boolean isRoyalFlush(Card[] cards){
        if(isStraight(cards)==true){
            if(isFlush(cards)==true){
            if(cards[0].getCardKind().ordinal()==8&&
                    cards[4].getCardKind().ordinal()==12){
                return true;
            }
            }
        }
        return false;
    }
    /**
     * the isStraightFlush method to identify if 5 cards are straight flush
     * @param cards the array of 5 cards
     * @return true if there are and false if there are not
     */
    public static boolean isStraightFlush(Card[] cards){
        if(isStraight(cards)==true){
            if(isFlush(cards)==true){
                return true;
            }
        }
        return false;
    }
    /**
     * Main method to run the PokerHand class
     * Create 10 lists. Each has 5 cards to test each situation of poker hand
     * @param args String parameter
     */
    public static void main(String[] args) {
        Card card1=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Ten);
        Card card2=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Jack);
        Card card3=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Queen);
        Card card4=new Card(Card.Cardsuit.Hearts,Card.Cardkind.King);
        Card card5=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Ace);
        
        Card card6=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Six);
        Card card7=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Seven);
        Card card8=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Eight);
        Card card9=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Nine);
        Card card10=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Ten);
        
        Card card11=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Queen);
        Card card12=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Queen);
        Card card13=new Card(Card.Cardsuit.Spades,Card.Cardkind.Queen);
        Card card14=new Card(Card.Cardsuit.Diamonds,Card.Cardkind.Queen);
        Card card15=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Five);
        
        Card card16=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Ace);
        Card card17=new Card(Card.Cardsuit.Diamonds,Card.Cardkind.Ace);
        Card card18=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Ace);
        Card card19=new Card(Card.Cardsuit.Clubs,Card.Cardkind.King);
        Card card20=new Card(Card.Cardsuit.Spades,Card.Cardkind.King);
        
        Card card21=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Two);
        Card card22=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Four);
        Card card23=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Six);
        Card card24=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Eight);
        Card card25=new Card(Card.Cardsuit.Hearts,Card.Cardkind.King);
        
        Card card26=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Four);
        Card card27=new Card(Card.Cardsuit.Spades,Card.Cardkind.Five);
        Card card28=new Card(Card.Cardsuit.Diamonds,Card.Cardkind.Six);
        Card card29=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Seven);
        Card card30=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Eight);
        
        Card card31=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Eight);
        Card card32=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Nine);
        Card card33=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Queen);
        Card card34=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Queen);
        Card card35=new Card(Card.Cardsuit.Diamonds,Card.Cardkind.Queen);
        
        Card card36=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Five);
        Card card37=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Nine);
        Card card38=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Nine);
        Card card39=new Card(Card.Cardsuit.Diamonds,Card.Cardkind.Ten);
        Card card40=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Ten);
        
        Card card41=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Ten);
        Card card42=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Ten);
        Card card43=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Jack);
        Card card44=new Card(Card.Cardsuit.Hearts,Card.Cardkind.King);
        Card card45=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Ace);
        
        Card card46=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Two);
        Card card47=new Card(Card.Cardsuit.Clubs,Card.Cardkind.Three);
        Card card48=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Eight);
        Card card49=new Card(Card.Cardsuit.Diamonds,Card.Cardkind.Ten);
        Card card50=new Card(Card.Cardsuit.Hearts,Card.Cardkind.Ace);
        
        
        Card cards[];
        cards=new Card[]{card1,card2,card3,card4,card5};
        Card cardss[];
        cardss=new Card[]{card6,card7,card8,card9,card10};
        Card cardsss[];
        cardsss=new Card[]{card11,card12,card13,card14,card15};
        Card carda[];
        carda=new Card[]{card16,card17,card18,card19,card20};
        Card cardf[];
        cardf=new Card[]{card21,card22,card23,card24,card25};
        Card cardst[];
        cardst=new Card[]{card26,card27,card28,card29,card30};
        Card cardtk[];
        cardtk=new Card[]{card31,card32,card33,card34,card35};
        Card cardtp[];
        cardtp=new Card[]{card36,card37,card38,card39,card40};
        Card cardop[];
        cardop=new Card[]{card41,card42,card43,card44,card45};
        Card cardhc[];
        cardhc=new Card[]{card46,card47,card48,card49,card50};
        
        System.out.println("Royal Flush: "+classifyHand(cards));
        System.out.println("Straight Flush: "+classifyHand(cardss));
        System.out.println("Four of a kind: "+classifyHand(cardsss));
        System.out.println("Full House: "+classifyHand(carda));
        System.out.println("Flush: "+classifyHand(cardf));
        System.out.println("Straight: "+classifyHand(cardst));
        System.out.println("Three of Kind: "+classifyHand(cardtk));
        System.out.println("Two Pair: "+classifyHand(cardtp));
        System.out.println("One Pair: "+classifyHand(cardop));
        System.out.println("High Card: "+classifyHand(cardhc));
        
        if(classifyHand(cards)==HandType.ROYAL_FLUSH){
            System.out.println("Royal Flush: Passed");
            if(classifyHand(cardss)==HandType.STRAIGHT_FLUSH){
                System.out.println("Straight Flush: Passed");
                if(classifyHand(cardsss)==HandType.FOUR_OF_KIND){
                    System.out.println("Four of Kind: Passed");
                    if(classifyHand(carda)==HandType.FULL_HOUSE){
                        System.out.println("Full House: Passed");
                        if(classifyHand(cardf)==HandType.FLUSH){
                            System.out.println("Flush: Passed");
                            if(classifyHand(cardst)==HandType.STRAIGHT){
                                System.out.println("Straight: Passed");
                                if(classifyHand(cardtk)==HandType.THREE_OF_KIND){
                                    System.out.println("Three of Kind: Passed");
                                    if(classifyHand(cardtp)==HandType.TWO_PAIR){
                                        System.out.println("Two Pair: Passed");
                                        if(classifyHand(cardop)==HandType.PAIR){
                                            System.out.println("Pair: Passed");
                                            if(classifyHand(cardhc)==HandType.HIGH_CARD){
                                                System.out.println("High Card: Passed");
                                                System.out.println("total result: Passed");
                                            }
                                            else{
                                                System.out.println("Not Passed");
        }
                                        }
                                        
    }
}
                            }
                        }
                    }
                }
            }
        }
     }
}
