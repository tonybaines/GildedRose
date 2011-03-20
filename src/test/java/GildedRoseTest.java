import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class GildedRoseTest {
	

	@Test
	public void whenAnItemWithoutSpecialRulesIsUpdatedItsQualityAndSellInDaysValuesDecrease() {
		// Given a list with a 'standard' item
		List<Item> items = new ArrayList<Item>();
        items.add(new Item("Nothing Special", 10, 20));
		GildedRose.items = items;
		// When the updateQuality() method is called
		GildedRose.updateQuality();
		// Then the properties of the item should have decreased
		assertEquals(19, GildedRose.items.get(0).getQuality());
		assertEquals(9, GildedRose.items.get(0).getSellIn());
	}
	
	@Test
	public void whenAnItemWithoutSpecialRulesThatIsPastItsSellByDateIsUpdatedTheQualityDecreasesTwiceAsFast() {
		// Given a list with a 'standard' item which is past its Sell-By date
		List<Item> items = new ArrayList<Item>();
        items.add(new Item("Nothing Special", -1, 20));
		GildedRose.items = items;
		// When the updateQuality() method is called
		GildedRose.updateQuality();
		// Then the properties of the item should have decreased, and quality should have decreased by two
		assertEquals(18, GildedRose.items.get(0).getQuality());
		assertEquals(-2, GildedRose.items.get(0).getSellIn());
	}
	
	@Test
	public void whenAnItemWithoutSpecialRulesHasAZeroQualityIsUpdatedItsSellInDaysValuesDecreasesButQualityStaysTheSame() {
		// Given a list with a 'standard' item
		List<Item> items = new ArrayList<Item>();
        items.add(new Item("Nothing Special", 10, 0));
		GildedRose.items = items;
		// When the updateQuality() method is called
		GildedRose.updateQuality();
		// Then the sell-in-days of the item should have decreased, but the quality should still be zero
		assertEquals(0, GildedRose.items.get(0).getQuality());
		assertEquals(9, GildedRose.items.get(0).getSellIn());
	}
}
