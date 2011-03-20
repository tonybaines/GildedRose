import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class GildedRoseTest {
	private static final String NOTHING_SPECIAL = "Elixir of the Mongoose";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	
	private List<Item> items = new ArrayList<Item>();

	@Before public void tearDown() {
		items = new ArrayList<Item>();
	}
	
	@Test public void whenAnItemWithoutSpecialRulesIsUpdatedItsQualityAndSellInDaysValuesDecrease() {
		givenASingleItem(new Item(NOTHING_SPECIAL, 10, 20));
		afterAnUpdateTheExpectedSellInAndQualityAre(9, 19);
	}
	
	@Test public void whenAnItemWithoutSpecialRulesThatIsPastItsSellByDateIsUpdatedTheQualityDecreasesTwiceAsFast() {
		givenASingleItem(new Item(NOTHING_SPECIAL, -1, 20));
		afterAnUpdateTheExpectedSellInAndQualityAre(-2, 18);
	}
	
	@Test public void whenAnItemWithoutSpecialRulesHasAZeroQualityIsUpdatedItsSellInDaysValuesDecreasesButQualityStaysTheSame() {
		givenASingleItem(new Item(NOTHING_SPECIAL, 10, 0));
		afterAnUpdateTheExpectedSellInAndQualityAre(9, 0);
	}
	
	@Test public void whenAgedBrieIsUpdatedItsQualityIncreases() {
		givenASingleItem(new Item(AGED_BRIE, 10, 20));
		afterAnUpdateTheExpectedSellInAndQualityAre(9, 21);
	}
	
	@Test public void whenAgedBrieIsUpdatedItsQualityCanNeverIncreaseBeyondFifty() {
		givenASingleItem(new Item(AGED_BRIE, 10, 50));
		afterAnUpdateTheExpectedSellInAndQualityAre(9, 50);
	}
	
	@Test public void whenSulfurasIsUpdatedNothingChanges() {
		givenASingleItem(new Item(SULFURAS, 10, 80));
		afterAnUpdateTheExpectedSellInAndQualityAre(10, 80);
	}
	
	@Test public void whenBackstagePassesAreUpdatedTheQualityIncreasesByTwoWithinTenDaysOrLessOfTheirSellBy() {
		givenASingleItem(new Item(BACKSTAGE_PASSES, 10, 48));
		afterAnUpdateTheExpectedSellInAndQualityAre(9, 50);
	}
	
	@Test public void whenBackstagePassesAreUpdatedTheQualityIncreasesByThreeWithinFiveDaysOrLessOfTheirSellBy() {
		givenASingleItem(new Item(BACKSTAGE_PASSES, 5, 47));
		afterAnUpdateTheExpectedSellInAndQualityAre(4, 50);
	}
	
	@Test public void whenBackstagePassesAreUpdatedAndTheQualityIncreasesItCannotGoBeyondFifty() {
		givenASingleItem(new Item(BACKSTAGE_PASSES, 10, 49));
		afterAnUpdateTheExpectedSellInAndQualityAre(9, 50);
	}
	
	@Test public void whenBackstagePassesAreUpdatedAndTheSellByDateHasPassedTheirQualityDropsToZero() {
		givenASingleItem(new Item(BACKSTAGE_PASSES, 0, 50));
		afterAnUpdateTheExpectedSellInAndQualityAre(-1, 0);
	}
	
	// Test Helpers
	private void afterAnUpdateTheExpectedSellInAndQualityAre(int expectedSellIn, int expectedQuality) {
		GildedRose.updateQuality();
		Item item = GildedRose.items.get(0);
		assertEquals(expectedQuality, item.getQuality());
		assertEquals(expectedSellIn, item.getSellIn());
	}

	private void givenASingleItem(Item item) {
		items.add(item);
		GildedRose.items = items;
	}
}
