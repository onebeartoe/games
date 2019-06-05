
import unittest

from MemoryCardsGame import MemoryCardsGame
from MemoryCardsGameCannedData import MemoryCardsGameCannedData
from MemoryCardsGameResponse import MemoryCardsGameResponse

class MemoryCardsGameEndOfGameSpecification(unittest.TestCase):

    def setUp(self):
        self.cannedData = MemoryCardsGameCannedData()

    def test_endGame_loss(self):
        """
            guess 1 covered card
            guess 2 mis-match

            assert mismatch response
            assert round 2
            asser

            guess 1 covered card
            guess 2 mis-match

            assert end of game loss response
        """

        implementation = MemoryCardsGame();
        cards = self.cannedData.validCardSetCountOf2();
        implementation.setCards(cards);
        implementation.startGame();

        implementation.selectCard1();
        mismatch1 = implementation.selectCard7();  # not a match in this data set
        self.assertEqual(mismatch1, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MISMATCH);
        self.assertEqual(implementation.round, 2);

        implementation.selectCard2();
        mismatch2 = implementation.selectCard8();  # not a match in this data set
        self.assertEqual(mismatch2, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MISMATCH_END_OF_GAME_LOSE);




    def test_endGame_win(self):
        """
            start game

            select all matches

            assert end of game win response
        """

        implementation = MemoryCardsGame();
        cards = self.cannedData.validCardSetCountOf2();
        implementation.setCards(cards);
        implementation.startGame();

        implementation.selectCard1();
        implementation.selectCard2();
        implementation.selectCard3();
        implementation.selectCard4();
        implementation.selectCard5();
        implementation.selectCard6();
        implementation.selectCard7();
        implementation.selectCard8();
        implementation.selectCard9();
        implementation.selectCard10();
        implementation.selectCard11();
        response = implementation.selectCard12();

        self.assertEqual(response, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH_END_OF_GAME_WIN);        
