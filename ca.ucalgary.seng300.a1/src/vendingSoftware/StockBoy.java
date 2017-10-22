package vendingSoftware;

import org.lsmr.vending.PopCan;
import org.lsmr.vending.hardware.*;

public class StockBoy implements PopCanRackListener{
	private VendingMachine machine;
	private ShopKeeper shopKeeper;
	
	public StockBoy(VendingMachine machine) {
		this.machine = machine;
	}
	
	//Stockboy doesn't need to talk to accountant, and this makes set up a little more merciful
	//also adds stockBoy to each popRack's listener list... i think
	public void configureSB(ShopKeeper shopKeeper) {
		this.shopKeeper = shopKeeper;
		for(int i = 0; i == machine.getNumberOfPopCanRacks(); i++)
			machine.getPopCanRack(i).register(this);
			
	}
	
//============================================================================================================

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// software disabling is redundant, as all hardware will be offline	
	}
	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		//stub, see above
	}
	
//=============================================================================================================
	
	/**
	 * Attempt to dispense the requested item via popcanrack, if it works then it will notify shopkeeper
	 * otherwise the exception will be passed to shopkeeper
	 * 
	 * @param productOrdered
	 * @throws Exception
	 */
	public void tryToDeliver(int productOrdered) throws Exception {
		try {
			machine.getPopCanRack(productOrdered).dispensePopCan();
			shopKeeper.productDelivered(productOrdered);
		} catch (DisabledException | EmptyException | CapacityExceededException e) {
			// TODO Auto-generated catch block
			throw e;
		}	
	}

//=============================================================================================================
	
	/**
	 * Stockboy, may keep inventory in the future, or otherwise need to track pop levels, but currently these
	 * methods can remain as stubs, until additional features are needed.
	 */
	
	@Override
	public void popCanAdded(PopCanRack popCanRack, PopCan popCan) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void popCanRemoved(PopCanRack popCanRack, PopCan popCan) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void popCansFull(PopCanRack popCanRack) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void popCansEmpty(PopCanRack popCanRack) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void popCansLoaded(PopCanRack rack, PopCan... popCans) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void popCansUnloaded(PopCanRack rack, PopCan... popCans) {
		// TODO Auto-generated method stub
		
	}

}
