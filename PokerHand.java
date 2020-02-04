/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.Arrays;

/**
 *
 * @author votha
 */
public class PokerHand {
    public enum HandType{
        HIGH_CARD,PAIR,TWO_PAIR,THREE_OF_KIND,STRAIGHT,FLUSH,FULL_HOUSE,FOUR_OF_KIND,
        STRAIGHT_FLUSH,ROYAL_FLUSH
    }
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
    public static boolean isTwoPair(Card[] cards){
        if(isPair(cards)==true){
            if(isFourKind(cards)==false){
                if(isPair(Arrays.copyOfRange(cards, 0, 2))==true&&isPair(Arrays.copyOfRange(cards, 2, 4))==true)
                    return true;
                if(isPair(Arrays.copyOfRange(cards, 0, 2))==true&&isPair(Arrays.copyOfRange(cards, 3, 5))==true){
                    return true;
                }
                if(isPair(Arrays.copyOfRange(cards, 1, 3))==true&&isPair(Arrays.copyOfRange(cards, 3, 5))==true){
                    return true;
                }
            }
        }
        return false;
    }
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
    public static boolean isFullHouse(Card[] cards){
        if(isThreeKind(Arrays.copyOfRange(cards, 0, 3))==true&&isPair(Arrays.copyOfRange(cards, 3, 5))==true){
            return true;
        }
        if(isThreeKind(Arrays.copyOfRange(cards, 3, 5))==true&&isPair(Arrays.copyOfRange(cards, 0, 3))==true){
            return true;
        }
         return false;
    }
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
    public static boolean isStraight(Card[] cards){
        if(isPair(cards)==false){
        if((int)cards[0].getCardKind().ordinal()<(int)cards[1].getCardKind().ordinal()&&
                (int)cards[1].getCardKind().ordinal()<(int)cards[2].getCardKind().ordinal()&&
                (int)cards[2].getCardKind().ordinal()<(int)cards[3].getCardKind().ordinal()&&
                (int)cards[3].getCardKind().ordinal()<(int)cards[4].getCardKind().ordinal()){
                    return true;
           }
        }
        return false; 
    }
    public static boolean isFlush(Card[] cards){
        if(cards[0].getCardSuit()==cards[1].getCardSuit()&&
                    cards[1].getCardSuit()==cards[2].getCardSuit()&&
                    cards[2].getCardSuit()==cards[3].getCardSuit()&&
                    cards[3].getCardSuit()==cards[4].getCardSuit()){
            return true;
        }
        return false;
    }
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
    public static boolean isStraightFlush(Card[] cards){
        if(isStraight(cards)==true){
            if(isFlush(cards)==true){
                return true;
            }
        }
        return false;
    }
}
