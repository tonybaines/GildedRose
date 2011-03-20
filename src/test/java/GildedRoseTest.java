import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;


public class GildedRoseTest {
	private List<Item> items = new ArrayList<Item>();

	@After
	public void tearDown() {
		items = new ArrayList<Item>();
	}
	
	@Test
	public void whenAnItemWithoutSpecialRulesIsUpdatedItsQualityAndSellInDaysValuesDecrease() {
		// Given a list with a 'standard' item
		givenASingleItem(new Item("Nothing Special", 10, 20));
		// When the updateQuality() method is called
		GildedRose.updateQuality();
		// Then the properties of the item should have decreased
		assertQualityAndSellInEquals(19, 9, GildedRose.items.get(0));
	}
	
	@Test
	public void whenAnItemWithoutSpecialRulesThatIsPastItsSellByDateIsUpdatedTheQualityDecreasesTwiceAsFast() {
		// Given a list with a 'standard' item which is past its Sell-By date
		givenASingleItem(new Item("Nothing Special", -1, 20));
		// When the updateQuality() method is called
		GildedRose.updateQuality();
		// Then the properties of the item should have decreased, and quality should have decreased by two
		assertQualityAndSellInEquals(18, -2, GildedRose.items.get(0));
	}
	
	@Test
	public void whenAnItemWithoutSpecialRulesHasAZeroQualityIsUpdatedItsSellInDaysValuesDecreasesButQualityStaysTheSame() {
		// Given a list with a 'standard' item which already has zero quality
		givenASingleItem(new Item("Nothing Special", 10, 0));
		// When the updateQuality() method is called
		GildedRose.updateQuality();
		// Then the sell-in-days of the item should have decreased, but the quality should still be zero
		assertQualityAndSellInEquals(0, 9, GildedRose.items.get(0));
	}

	private void assertQualityAndSellInEquals(int expectedQuality, int expectedSellIn, Item item) {
		assertEquals(expectedQuality, item.getQuality());
		assertEquals(expectedSellIn, item.getSellIn());
	}

	private void givenASingleItem(Item item) {
		items.add(item);
		GildedRose.items = items;
	}
}
