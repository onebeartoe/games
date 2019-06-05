
#PYTHONPATH=~/Versioning/owner/github/onebeartoe/games/memory/cards/game/src/main/python
#PYTHONPATH=PYTHONPATH:~/Versioning/owner/github/onebeartoe/games/memory/cards/game/src/main/python/onebeartoe/games/memory
#echo 'pythonpath - '
#echo $PYTHONPATH


python3 -m unittest -v -f MemoryCardsGameInitializationSpecification

python3 -m unittest -v -f MemoryCardsGameCardSelectionSpecification

python3 -m unittest -v -f MemoryCardsGameEndOfRoundSpecification

python3 -m unittest -v -f MemoryCardsGameEndOfGameSpecification
