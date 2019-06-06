
import unittest

from MemoryCardsGame import MemoryCardsGame
from MemoryCardsGameCannedData import MemoryCardsGameCannedData
from MemoryCardsGameResponse import MemoryCardsGameResponse

class MemoryCardsGameCardSelectionSpecification(unittest.TestCase):

    def setUp(self):
        self.cannedData = MemoryCardsGameCannedData()

    def test_selection(self):
        implementation = MemoryCardsGame()
        cards = self.cannedData.validCardSetAllTheSame()
        implementation.setCards(cards);
        implementation.startGame();

        # pair 1
        response1 = implementation.selectCard1();
        self.assertEqual(response1, MemoryCardsGameResponse.GUESS_ONE_ACCEPTED);
        response2 = implementation.selectCard2();
        self.assertEqual(response2, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH);

        # pair 2
        response3 = implementation.selectCard3();
        self.assertEqual(response3, MemoryCardsGameResponse.GUESS_ONE_ACCEPTED);
        response4 = implementation.selectCard4();
        self.assertEqual(response4, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH);

        # pair 3
        response5 = implementation.selectCard5();
        self.assertEqual(response5, MemoryCardsGameResponse.GUESS_ONE_ACCEPTED);
        response6 = implementation.selectCard6();
        self.assertEqual(response6, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH);




    def test_selection_failsGuess1(self):
        # get past round 1.  in round 2 for guess 1 select a revealed card and expect rejected request
        implementation = MemoryCardsGame();
        cards = self.cannedData.validCardSetAllTheSame();
        implementation.setCards(cards);
        implementation.startGame();

        # get pass round 1
        response1 = implementation.selectCard1();
        self.assertEqual(response1, MemoryCardsGameResponse.GUESS_ONE_ACCEPTED);
        response2 = implementation.selectCard2();
        self.assertEqual(response2, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH);

        response3 = implementation.selectCard1();  # again
        self.assertEqual(response3, MemoryCardsGameResponse.GUESS_REJECTED_CARD_ALREADY_REVEALED)



    def test_selection_failsGuess2(self):
        # get past round 1 and on guess 2 of round 2 select a revealed card and expect rejected request
        implementation = MemoryCardsGame();
        cards = self.cannedData.validCardSetAllTheSame();
        implementation.setCards(cards);
        implementation.startGame();

        # pair 1
        response1 = implementation.selectCard1();
        self.assertEqual(response1, MemoryCardsGameResponse.GUESS_ONE_ACCEPTED);
        response2 = implementation.selectCard2();
        self.assertEqual(response2, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH);

        # pair 2
        response3 = implementation.selectCard3();
        self.assertEqual(response3, MemoryCardsGameResponse.GUESS_ONE_ACCEPTED);


        response4 = implementation.selectCard2();   # again
        self.assertEqual(response4, MemoryCardsGameResponse.GUESS_REJECTED_CARD_ALREADY_REVEALED)
