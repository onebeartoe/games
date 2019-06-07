
# install dependencies:
# $ pip3 install html-testRunner


# import dependencies
from unittest import TestLoader, TestSuite
from HtmlTestRunner import HTMLTestRunner

from MemoryCardsGameInitializationSpecification import MemoryCardsGameInitializationSpecification
from MemoryCardsGameCardSelectionSpecification import MemoryCardsGameCardSelectionSpecification
from MemoryCardsGameEndOfRoundSpecification import MemoryCardsGameEndOfRoundSpecification
from MemoryCardsGameEndOfGameSpecification import MemoryCardsGameEndOfGameSpecification

# setup unit test suites
initializationTests = TestLoader().loadTestsFromTestCase(MemoryCardsGameInitializationSpecification)
selectionTests = TestLoader().loadTestsFromTestCase(MemoryCardsGameCardSelectionSpecification)
endOfRoundTests = TestLoader().loadTestsFromTestCase(MemoryCardsGameEndOfRoundSpecification)
endOfGameTests = TestLoader().loadTestsFromTestCase(MemoryCardsGameEndOfGameSpecification)

suite = TestSuite([initializationTests, selectionTests, endOfRoundTests, endOfGameTests])

# configure HTML report
runner = HTMLTestRunner(add_timestamp=False, combine_reports=True, output='target', report_name="unit-test-report", verbosity=2)

runner.run(suite)
