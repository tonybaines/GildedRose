import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;


public class GildedRoseTest {
	private List<Item> items = new ArrayList<Item>();

	@After public void tearDown() {
		items = new ArrayList<Item>();
	}
	
	@Test public void whenAnItemWithoutSpecialRulesIsUpdatedItsQualityAndSellInDaysValuesDecrease() {
		givenASingleItem(new Item("Nothing Special", 10, 20));
		GildedRose.updateQuality();
		assertSellInAndQualityEquals(9, 19, GildedRose.items.get(0));
	}
	
	@Test public void whenAnItemWithoutSpecialRulesThatIsPastItsSellByDateIsUpdatedTheQualityDecreasesTwiceAsFast() {
		givenASingleItem(new Item("Nothing Special", -1, 20));
		GildedRose.updateQuality();
		assertSellInAndQualityEquals(-2, 18, GildedRose.items.get(0));
	}
	
	@Test public void whenAnItemWithoutSpecialRulesHasAZeroQualityIsUpdatedItsSellInDaysValuesDecreasesButQualityStaysTheSame() {
		givenASingleItem(new Item("Nothing Special", 10, 0));
		GildedRose.updateQuality();
		assertSellInAndQualityEquals(9, 0, GildedRose.items.get(0));
	}
	
	@Test public void whenAgedBrieIsUpdatedItsQualityIncreases() {
		givenASingleItem(new Item("Aged Brie", 10, 20));
		GildedRose.updateQuality();
		assertSellInAndQualityEquals(9, 21, GildedRose.items.get(0));
	}
	
	@Test public void whenAgedBrieIsUpdatedItsQualityCanNeverIncreaseBeyondFifty() {
		givenASingleItem(new Item("Aged Brie", 10, 50));
		GildedRose.updateQuality();
		assertSellInAndQualityEquals(9, 50, GildedRose.items.get(0));
	}
	
	// Test Helpers
	private void assertSellInAndQualityEquals(int expectedSellIn, int expectedQuality, Item item) {
		assertEquals(expectedQuality, item.getQuality());
		assertEquals(expectedSellIn, item.getSellIn());
	}

	private void givenASingleItem(Item item) {
		items.add(item);
		GildedRose.items = items;
	}
}
