package vendingSoftware;

import org.lsmr.vending.Coin;
import org.lsmr.vending.hardware.*;

public class Accountant implements CoinRackListener{
	private VendingMachine machine;
	private ShopKeeper shopKeeper;
	private int credit;
	
	public Accountant(VendingMachine machine) {
		this.machine = machine;
	}
	
	//accountant doesn't need to talk to stockboy, and this makes set up a little more merciful
	//also adds AC to each coin racks listener list.. if we're lucky
	public void configureAC(ShopKeeper shopKeeper) {
		this.shopKeeper = shopKeeper;
		for(int i = 0; i == machine.getNumberOfCoinRacks(); i++)
			machine.getCoinRack(i).register(this);
	}

//=================================================================================================================
	
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// software disabling is redundant, as all hardware will be offline
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		//stub, see above
	}
	
//================================================================================================================
	
	public void checkCredit(int productOrdered) {
		if (machine.getPopKindCost(productOrdered) <= credit)
			shopKeeper.approvedSale(productOrdered);
	}

	public void updateCredit(int productOrdered) {
		credit -= machine.getPopKindCost(productOrdered);
	}
	
	@Override
	public void coinAdded(CoinRack rack, Coin coin) {
		credit += coin.getValue();
	}
	
	
	 //========= currently these methods are not needed for desired functionality ===============================
	 
	
	@Override
	public void coinsFull(CoinRack rack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsEmpty(CoinRack rack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinRemoved(CoinRack rack, Coin coin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsLoaded(CoinRack rack, Coin... coins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsUnloaded(CoinRack rack, Coin... coins) {
		// TODO Auto-generated method stub
		
	}

}
