
import unittest

from CardsAlreadyInitializedException import CardsAlreadyInitializedException
from InvalidPairsException import InvalidPairsException
from MemoryCard import MemoryCard
from MemoryCardsGame import MemoryCardsGame
from MemoryCardsGameCannedData import MemoryCardsGameCannedData
from MemoryCardsGameResponse import MemoryCardsGameResponse
from MemoryCardStates import MemoryCardStates
from TooFewCardsException import TooFewCardsException
from TooManyCardsException import TooManyCardsException

class TestMemoryCardsGameInitialization(unittest.TestCase):

    def setUp(self):
        self.cannedData = MemoryCardsGameCannedData()


    def test_initialization_allTheSameCard(self):
        implementation = MemoryCardsGame()

        cards = self.cannedData.validCardSetAllTheSame()

        gameResponse = implementation.setCards(cards)

        expected = MemoryCardsGameResponse.CARDS_INITIALIZED

        self.assertEqual(gameResponse, expected)


    def test_initialization_withDifferentPairs(self):
        implementation = MemoryCardsGame()

        cards = self.cannedData.validCardSetCountOf2()

        gameResponse = implementation.setCards(cards)

        expected = MemoryCardsGameResponse.CARDS_INITIALIZED

        self.assertEqual(gameResponse, expected)



    def test_initialization_fails_tooFewCards(self):
        implementation = MemoryCardsGame()

        cards = []

        for x in range(0, MemoryCardsGame.MAX_SIZE - 4):
            card = MemoryCard()
            card.value = 2
            cards.insert(x, card)

            with self.assertRaises(TooFewCardsException):
                implementation.setCards(cards)


    def test_initialization_fails_tooManyCards(self):
        implementation = MemoryCardsGame()

        cards = []

        for x in range(0, MemoryCardsGame.MAX_SIZE + 7):
            card = MemoryCard()
            card.value = 6
            cards.insert(x, card)

        with self.assertRaises(TooManyCardsException):
            implementation.setCards(cards)



    def test_initialization_fails_missingPairs(self):
        implementation = MemoryCardsGame()

        cards = []

        # fill all but one card
        for x in range(0, MemoryCardsGame.MAX_SIZE - 1):
            card = MemoryCard()
            card.value = 2
            cards.insert(x, card)

        card = MemoryCard()
        card.value = 8
        cards.insert(x, card)

        with self.assertRaises(InvalidPairsException):
            implementation.setCards(cards)


    def test_initialization_fails_resetingOfCardsAfterGameStart(self):
        implementation = MemoryCardsGame()

        cards = self.cannedData.validCardSetAllTheSame()
        response = implementation.setCards(cards)
        self.assertEqual(response, MemoryCardsGameResponse.CARDS_INITIALIZED)

        response = implementation.startGame()
        self.assertEqual(response, MemoryCardsGameResponse.GAME_IN_PROGRESS)

        secondCards = self.cannedData.validCardSetCountOf2()

        with self.assertRaises(CardsAlreadyInitializedException):
            implementation.setCards(secondCards)
