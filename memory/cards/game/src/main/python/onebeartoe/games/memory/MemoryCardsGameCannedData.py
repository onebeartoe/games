
from MemoryCard import MemoryCard
from MemoryCardsGame import MemoryCardsGame


class MemoryCardsGameCannedData(object):
    """ documentation """

    def __init__(self):
        self.game = MemoryCardsGame()
#        print("")

    def validCardSetAllTheSame(self):
        cards = []
        sameValue = 1

        for x in range(0, self.game.MAX_SIZE):
            card = MemoryCard()
            card.value = sameValue
            cards.insert(x, card)

        return cards


    def validCardSetCountOf2(self):
       cards = []

       sameValue1 = 1

       half = MemoryCardsGame.MAX_SIZE / 2
       half = int(half)

       for x in range(0, half):
           card = MemoryCard()
           card.value = sameValue1
           cards.insert(x, card)

       sameValue2 = 2

       for x in range(half, MemoryCardsGame.MAX_SIZE):
           card = MemoryCard()
           card.value = sameValue2
           cards.insert(x, card)

       return cards
