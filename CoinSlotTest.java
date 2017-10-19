package org.lsmr.vending.hardware;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.vending.Coin;


public class CoinSlotTest {

	@Test
	public void test() throws DisabledException {
		int[] vv = {1,-2,3,4,5};
		CoinChannel valid = null, invalid = null;
		Coin coin = new Coin(123);
		//Coin coin2 = new Coin(-2);
		Coin coin3 = new Coin(90909090);
		Coin coin4 = new Coin(5);
		Coin coin5 = new Coin(6);

		CoinSlot coinslot = new CoinSlot(vv);
		coinslot.connect(valid,invalid);
		coinslot.addCoin(coin);
		coinslot.addCoin(coin);
		//coinslot.addCoin(coin2);
		coinslot.addCoin(coin3);
		coinslot.addCoin(coin4);
		coinslot.addCoin(coin5);
		
		
		
		fail("Not yet implemented");
	}

}
