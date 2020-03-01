/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

/**
 *
 * @author votha
 */
public class Card {
    private Cardsuit CardSuit;
    private Cardkind CardKind;
    
    public enum Cardsuit{
        Spades,
        Hearts,
        Clubs,
        Diamonds
    }
    
    public enum Cardkind{
        Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten,
        Jack,Queen,King,Ace
    }
    
    public Card(Cardsuit Cardsuit, Cardkind Cardkind){
        
        CardSuit=Cardsuit;
        CardKind=Cardkind;
    }
    
    public Cardsuit getCardSuit(){
        return CardSuit;
    }
    
    public Cardkind getCardKind(){
        return CardKind;
    }
    
    @Override
    public String toString(){
        Cardkind kind=getCardKind();
        String CardKinds;
       if(kind==Cardkind.Two){
           CardKinds="2";
       }
       else if(kind==Cardkind.Three){
           CardKinds="3";
       }
       else if(kind==Cardkind.Four){
           CardKinds="4";
       }
       else if(kind==Cardkind.Five){
           CardKinds="5";
       }
       else if(kind==Cardkind.Six){
           CardKinds="6";
       }
       else if(kind==Cardkind.Seven){
           CardKinds="7";
       }
       else if(kind==Cardkind.Eight){
           CardKinds="8";
       }
       else if(kind==Cardkind.Nine){
           CardKinds="9";
       }
       else if(kind==Cardkind.Ten){
           CardKinds="10";
       }
       else if(kind==Cardkind.Jack){
           CardKinds="Jack";
       }
       else if(kind==Cardkind.Queen){
           CardKinds="Queen";
       }
       else if(kind==Cardkind.King){
           CardKinds="King";
       }
       else if(kind==Cardkind.Ace){
           CardKinds="Ace";
       }
       else {
           CardKinds=" ";
    }
       return CardKinds+" of "+getCardSuit();
    }
    
}

