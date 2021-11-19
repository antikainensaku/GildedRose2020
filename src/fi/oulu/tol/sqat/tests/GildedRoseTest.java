package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {
	@Test
    public void testMain() {
        String[] args = null;
        GildedRose.main(args);
    }
	
	@Test
    public void testAgedBrie_increase_and_sellin() {
        // create an inn, add an item, and simulate two days
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Aged Brie", 1, 0));
        inn.oneDay();
        inn.oneDay();

        // access a list of items, get the quality and sellIn
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        int sellIn = items.get(0).getSellIn();

        // assert quality has increased by two and sellIn gone negative
        assertEquals("Failed quality for Aged Brie", 3, quality);
        assertEquals("Failed sellIn for Aged Brie", -1, sellIn);
	}
	
	@Test
    public void testAgedBrie_increase_over50() {
        // create an inn, add an item, and simulate 60 days
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Aged Brie", 70, 0));
        for (int i = 1; i <= 60; i++) {
            inn.oneDay();
        }

        // access a list of items, get the quality
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();

        // assert quality isn't over 50
        assertEquals("Failed quality for Aged Brie", 50, quality);
	}
	
	@Test
    public void testAgedBrie_mutant() {
        // create an inn, add an item, and simulate one day
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Aged Brie", 0, 49));
        inn.oneDay();
        // access a list of items, get the quality
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();

        // assert quality is 50
        assertEquals("Failed quality for Aged Brie", 50, quality);
	}

	@Test
    public void testAgedBrie_is_over50() {
        // create an inn, add an item, and simulate one day
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Aged Brie", 0, 60));
        inn.oneDay();
        
        // access a list of items, get the quality
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();

        // assert quality is still 60
        assertEquals("Failed quality for Aged Brie", 60, quality);
	}
	
	@Test
    public void testBackstagePass() {
        // create an inn, add an item, and simulate 8 days
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 12, 0));
        for (int i = 1; i <= 8; i++) {
            inn.oneDay();
        }

        // access a list of items, get the quality and sellIn
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        int sellIn = items.get(0).getSellIn();
        
        // assert quality has increased by 2 + 2*5 + 1*3 = 15 and sellIn at 4
        assertEquals("Failed quality for Backstage passes to a TAFKAL80ETC concert", 15, quality);
        assertEquals("Failed sellIn for Backstage passes to a TAFKAL80ETC concert", 4, sellIn);
    }
	
	@Test
    public void testBackstagePass_sellIn_negative() {
        // create an inn, add an item, and simulate two days
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20));
        inn.oneDay();
        inn.oneDay();

        // access a list of items, get the quality and sellIn
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        int sellIn = items.get(0).getSellIn();
        	
        // assert quality has gone to 0 and sellIn to negative
        assertEquals("Failed quality for Backstage passes to a TAFKAL80ETC concert", 0, quality);
        assertEquals("Failed sellIn for Backstage passes to a TAFKAL80ETC concert", -1, sellIn);
    }
	
	@Test
    public void testSulfuras() {
        //create an inn, add an item, and simulate one day
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
        inn.oneDay();
        
        //access a list of items, get the quality of the one set
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        int sellIn = items.get(0).getSellIn();
        
        //assert quality hasn't changed and sellIn is negative
        assertEquals("Failed quality for Sulfuras, Hand of Ragnaros", 80, quality);
        assertEquals("Failed sellIn for Sulfuras, Hand of Ragnaros", -1, sellIn);
    }
	
	@Test
    public void test_two_items() {
        //create an inn, add an item, and simulate one day
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 10, 80));
        inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
        inn.oneDay();
        
        //access a list of items, get the quality of the one set
        List<Item> items = inn.getItems();
        int sulf_quality = items.get(0).getQuality();
        int dex_quality = items.get(1).getQuality();
        
        //assert quality for both
        assertEquals("Failed quality for Sulfuras, Hand of Ragnaros", 80, sulf_quality);
        assertEquals("Failed quality for +5 Dexterity Vest", 19, dex_quality);
    }
	
	@Test
    public void dexterity_vest_quality_zerov2() {
        //create an inn, add an item, and simulate one day
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("+5 Dexterity Vest", 3, 6));
        
        for(int i=1;i<=6;i++){  
			inn.oneDay();
	    }
        
        //access a list of items, get the quality of the one set
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        //assert quality has decreased
        assertEquals("Failed quality for +5 Dexterity Vest", 0, quality);
    }
	
	@Test
    public void dexterity_vest_quality_zero() {
        // create an inn, add an item, and simulate one day
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("+5 Dexterity Vest", 0, 1));
        inn.oneDay();

        // access a list of items, get the quality
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();
        
        // assert quality has decreased to zero
        assertEquals("Failed quality for Dexterity Vest", 0, quality);
	}
        
	@Test
    public void degrade_twice_as_fast() {
        // create an inn, add an item, and simulate 11 days
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("+5 Dexterity Vest", 10, 20));

        for (int i = 1; i <= 11; i++) {
            inn.oneDay();
        }

        // access a list of items, get the quality
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();

        // assert quality has decreased by 10 + 2 (after sellIn = 0)
        assertEquals("Failed quality for Dexterity Vest", 8, quality);
    }
	
	@Test
    public void negative_quality() {
        // create an inn, add an item, and simulate two days
        GildedRose inn = new GildedRose();
        inn.setItem(new Item("+5 Dexterity Vest", 1, -1));
        inn.oneDay();
        inn.oneDay();

        // access a list of items, get the quality
        List<Item> items = inn.getItems();
        int quality = items.get(0).getQuality();

        // assert quality is still -1
        assertEquals("Failed quality for Dexterity Vest", -1, quality);
    }
	
}