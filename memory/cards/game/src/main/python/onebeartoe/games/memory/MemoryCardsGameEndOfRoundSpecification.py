
import unittest

from MemoryCardsGame import MemoryCardsGame
from MemoryCardsGameCannedData import MemoryCardsGameCannedData
from MemoryCardStates import MemoryCardStates

class MemoryCardsGameEndOfRoundSpecification(unittest.TestCase):

    def setUp(self):
        self.cannedData = MemoryCardsGameCannedData()

    def test_endOfRound(self):
        """
            note round 1
            select a valid guess 1
            select a valid guess 2
            assert round is now 2
            select a valid guess 1
            select a valid guess 2
            assert round is now 3
        """

        implementation = MemoryCardsGame();
        cards = self.cannedData.validCardSetAllTheSame();
        implementation.setCards(cards);
        implementation.startGame();

        # assert round 1
        roundNumber = implementation.round
        self.assertEqual(roundNumber, 1);

        # assert round 2
        implementation.selectCard1();
        implementation.selectCard2();
        roundNumber = implementation.round
        self.assertEqual(roundNumber, 2);

        # assert round 3
        implementation.selectCard3();
        implementation.selectCard4();
        roundNumber = implementation.round
        self.assertEqual(roundNumber, 3);

        # assert round 4
        implementation.selectCard5();
        implementation.selectCard6();
        roundNumber = implementation.round
        self.assertEqual(roundNumber, 4);


    def test_endOfRound_negative(self):
        """
            assert round is 1
            select a valid guess 1
            select an invalid guess 2
            assert round is still 1
            select a valid guess 2
            assert round is now 2
            select a valid guess 1
            select an invalid guess 2
            assert round is still 2
        """

        implementation = MemoryCardsGame();
        cards = self.cannedData.validCardSetAllTheSame();
        implementation.setCards(cards);
        implementation.startGame();

        # assert round 1
        roundNumber = implementation.round;
        self.assertEqual(roundNumber, 1);

        implementation.selectCard1()

        try:
            implementation.selectCard1(); # again
        except Exception:
            # expected
            pass

        self.assertEqual(implementation.round, 1); # still round 1;

        implementation.selectCard2();
        self.assertEqual(implementation.round, 2); # now round 2

        implementation.selectCard3();
        try:
            implementation.selectCard2(); # again
        except Exception:
            # expected
            pass

        self.assertEqual(implementation.round, 2); # still round 2;





    def test_endOfRound_guessCardsAreMarkedAsRevealed(self):
        """
            start game

            guess two matching cards

            assert guess 1 card is marked as revealed

            assert guess 2 card is marked as revealed
        """

        implementation = MemoryCardsGame();
        cards = self.cannedData.validCardSetAllTheSame();
        implementation.setCards(cards);
        implementation.startGame();

        implementation.selectCard1();
        implementation.selectCard2();

        self.assertEqual(implementation.getCardStatus1(), MemoryCardStates.REVEALED);
        self.assertEqual(implementation.getCardStatus2(), MemoryCardStates.REVEALED);

        implementation.selectCard3();
        implementation.selectCard4();

        self.assertEqual(implementation.getCardStatus3(), MemoryCardStates.REVEALED);
        self.assertEqual(implementation.getCardStatus4(), MemoryCardStates.REVEALED);





    def test_endOfRound_mismatchIsRecored(self):
        """
            start game

            assert mis-match count is 0

            guess 1 any covered card

            guess 2 a mismatch

            assert mis-match count is 1
        """

        implementation =  MemoryCardsGame();
        cards = self.cannedData.validCardSetCountOf2();
        implementation.setCards(cards);
        implementation.startGame();

        self.assertEqual(implementation.mismatchCount, 0);

        implementation.selectCard1();
        try:
            implementation.selectCard7(); # the 7th card starts the other set of cards (different from card 1)
        except Exception:
            # expected
            pass

        self.assertEqual(implementation.mismatchCount, 1);
