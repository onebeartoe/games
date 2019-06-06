
from MemoryCardStates import MemoryCardStates
from MemoryCardsGameResponse import MemoryCardsGameResponse

class MemoryCardsGameButtons(object):

    def __init__(self):
        self.cards = None
        self.guess1 = None
        self.guess2 = None
        self.mismatchCount = 0

    def allPairsFound(self):
        allFound = True

        for card in self.cards:
            if(card.state != MemoryCardStates.REVEALED):

                allFound = False;

                break;



        return allFound;




    def getCardStatus1(self) :
        card = self.cards[0];

        return card.state


    def getCardStatus2(self) :
        card = self.cards[1];

        return card.state

    def getCardStatus3(self) :
        card = self.cards[2];

        return card.state

    def getCardStatus4(self) :
        card = self.cards[3];

        return card.state

    def getCardStatus5(self) :
        card = self.cards[4];

        return card.state

    def getCardStatus6(self) :
        card = self.cards[5];

        return card.state

    def getCardStatus7(self) :
        card = self.cards[6];

        return card.state

    def getCardStatus8(self) :
        card = self.cards[7];

        return card.state

    def getCardStatus9(self) :
        card = self.cards[8];

        return card.state

    def getCardStatus10(self) :
        card = self.cards[9];

        return card.state

    def getCardStatus11(self) :
        card = self.cards[10];

        return card.state

    def getCardStatus12(self) :
        card = self.cards[11];

        return card.state


    def resetGuesses(self):
        self.guess1 = None
        self.guess2 = None

    def selectCard(self, card):

        response = None

        print("ploop")

        if(not self.inProgress):
            response = MemoryCardsGameResponse.GUESS_REJECTED_GAME_NOT_IN_PROGRESS


        elif(card.state == MemoryCardStates.REVEALED):
            response = MemoryCardsGameResponse.GUESS_REJECTED_CARD_ALREADY_REVEALED


        else:
            card.state = MemoryCardStates.REVEALED



            if(self.guess1 == None):
                self.guess1 = card;
                response = MemoryCardsGameResponse.GUESS_ONE_ACCEPTED;
            else:
                self.guess2 = card;

                if(self.guess1.value == self.guess2.value):
                    allPairsFound = self.allPairsFound();
                    if(allPairsFound):
                        response = MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH_END_OF_GAME_WIN;

                    else:
                        response = MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH;

                else:
                    self.mismatchCount += 1

                    # reset the card states
                    guessIndex1 = self.guess1.value - 1
                    print("reset guess 1 index: ", guessIndex1)
                    self.cards[guessIndex1].state = MemoryCardStates.COVERED # guess 1 card
                    card.state = MemoryCardStates.COVERED   # guess 2 card

                    if(self.mismatchCount == 2):
                        response = MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MISMATCH_END_OF_GAME_LOSE;
                    else:
                        response = MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MISMATCH;

                self.round += 1;

                self.resetGuesses();

        return response;


    def selectCard1(self):
        card = self.cards[0]

        return self.selectCard(card)


    def selectCard2(self):
        card = self.cards[1]

        return self.selectCard(card)

    def selectCard3(self):
        card = self.cards[2]

        return self.selectCard(card)

    def selectCard4(self):
        card = self.cards[3]

        return self.selectCard(card)

    def selectCard5(self):
        card = self.cards[4]

        return self.selectCard(card)

    def selectCard6(self):
        card = self.cards[5]

        return self.selectCard(card)

    def selectCard7(self):
        card = self.cards[6]

        return self.selectCard(card)

    def selectCard8(self):
        card = self.cards[7]

        return self.selectCard(card)

    def selectCard9(self):
        card = self.cards[8]

        return self.selectCard(card)

    def selectCard10(self):
        card = self.cards[9]

        return self.selectCard(card)

    def selectCard11(self):
        card = self.cards[10]

        return self.selectCard(card)

    def selectCard12(self):
        card = self.cards[11]

        return self.selectCard(card)
