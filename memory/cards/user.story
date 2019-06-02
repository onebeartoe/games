
As a touch screen user I need a memory card game, so that I have a fun game to play.

Acceptance Criteria:

tc 1 - The game has 12 cards with 6 matching pairs and
tc 2 - the game's cards are only set when a game is not in progress and
tc 3 - the game ends in a user loss when the user guesses 2 mis-matches and
tc 4 - the game ends in a user win  when the user matches all card pairs and
tc 5 - REMOVED - cards have two states; revealed or covered and
tc 6 - a round ends when the user makes two valid guesses and,
tc 7 - the user can select any covered card as guess 1 or guess 2 and
tc 8 - an already revealed card is not allowed to be marked as guess 1 or 2 and
tc 9 - a round does not end if the user make a valid guess one but makes an invalid guess 2 and
tc10 - at the end of a round, the guess 1 and guess 2 cards are marked as revealed if the cards match and
tc11 - at the end of a round, a mis-match is recorded if the guess 1 and guess 2 cards do not match.


categories

    initialization

        1, 2, 5

    game play rules

        current turn selection

            7, 8 

        end of round

            6, 9, 10, 11

        end of game

            3, 4


