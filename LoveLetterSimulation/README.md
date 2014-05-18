LoveLetterSimulation
====================

For modeling the game mechanics of Love Letter.

Class overview:
-Player
-Card
-Deck
-Game

Game class:
Attributes
-Has an array of Players, players
-Has a Deck, deck
Methods
-public void start()

Player class:
Attributes
-Has an array of Cards, hand
-Has an array of Cards, discard
Methods
-public void draw(Deck drawPile)

Deck class:
Attributes
-Has an array of Cards, deck
-Has an int representing number of cards, count
Methods
-public Deck(int numberOfPLayers)
-public int getCount()
-public void decrement()
-private boolean isEmpty()

Card class:
Attributes
-Has an int, value
-Has a String, title
-Has a String, text
Methods
-public void discard()

Consistency of a Deck (16 cards):
5 Guards at point value 1: Name a non-Guard card and choose another player. If that player has that card, he or she is out of the round.
2 Priests at point value 2: Look at another player's hand.
2 Barons at point value 3: You and another player secretly compare hands. The player with the lower value is out of the round.
2 Handmaids at point value 4: Until your next turn, ignore all effects from other players' cards.
2 Princes at point value 5: Choose any player (including yourself) to discard his or her hand and draw a new card.
1 King at point value 6: Trade hands with another player of your choice.
1 Countess at point value 7: If you have this card and the King or Prince in your hand, you must discard this card.
1 Princess at point value 8: If you discard this card, you are out of the round.  


