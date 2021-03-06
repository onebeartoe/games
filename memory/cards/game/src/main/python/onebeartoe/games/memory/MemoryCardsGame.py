
from CardsAlreadyInitializedException import CardsAlreadyInitializedException
from InvalidPairsException import InvalidPairsException
from MemoryCardsGameButtons import MemoryCardsGameButtons
from MemoryCardsGameResponse import MemoryCardsGameResponse
from MemoryCardStates import MemoryCardStates
from TooFewCardsException import TooFewCardsException
from TooManyCardsException import TooManyCardsException

class MemoryCardsGame(MemoryCardsGameButtons):
#class MemoryCardsGame(object):
    """ documentation """

    MAX_SIZE = 12

    def __init__(self):
        super().__init__()

        self.inProgress = False
        self.round = 1


    def hasValidPairs(self, cards):
        copy = cards.copy()
        copy.sort(key=self.sorter)

        half = self.MAX_SIZE / 2
        half = int(half)

        validPairs = True

        for x in range(0, half):
            i = x * 2

            first = copy[i]
            second = copy[i+1]

            if(first.value != second.value):
                validPairs = False
                break

        return validPairs


    def resetGame(self):

        self.resetGuesses()

        for card in self.cards:
            card.state = MemoryCardStates.COVERED

        self.inProgress = False
        self.round = 1


    def sorter(self, card):
        return card.value


    def setCards(self, cards):
        if(self.inProgress):
            raise CardsAlreadyInitializedException()

        if(cards == None or len(cards) < self.MAX_SIZE):
            raise TooFewCardsException()

        if( len(cards) > self.MAX_SIZE):
            raise TooManyCardsException()

        if( not self.hasValidPairs(cards) ):
            raise InvalidPairsException()

        self.cards = cards

        response = MemoryCardsGameResponse.CARDS_INITIALIZED;

        return response




    def startGame(self):
        if(self.cards == None):
            raise Exception("the cards have not been set")

        self.inProgress = True

        return MemoryCardsGameResponse.GAME_IN_PROGRESS
