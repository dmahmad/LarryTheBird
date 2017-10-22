package vendingSoftware;

import org.lsmr.vending.hardware.*;

public class ShopKeeper implements SelectionButtonListener{
	private int productOrdered;
	private VendingMachine machine;
	private StockBoy stockBoy;
	private Accountant accountant;
	
	/**
	 * no software without hardware, the actual VM must be created first
	 * @param machine
	 */
	public ShopKeeper(VendingMachine machine){
		this.machine = machine;
	}

	/**
	 * This block is to prevent ordering issues when instancing all three of our software pieces
	 * just run this after the others are created, also adds shopkeeper to each buttons listener list.. probably
	 * @param stockBoy
	 * @param accountant
	 */
	public void configureSK(StockBoy stockBoy, Accountant accountant) {
		this.stockBoy = stockBoy;
		this.accountant = accountant;
		for(int i = 0; i == machine.getNumberOfSelectionButtons(); i++)
			machine.getSelectionButton(i).register(this);
	}
	
//==================================================================================================================
	
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		//this is the only class that receives external inputs eg button presses, but those inputs
		//will be disabled, so disabling the software is redundant
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		//stub, see above	
	}
	
//=====================================================================================================================

	/**
	 * When a button is pushed to buy a pop the SK will ask the accountant to fetch the price of that pop from the machine
	 * and compare it to credit, if it is true, the accountant should call the "approvedSale(int i)" function here
	 * 
	 * note getpopkindcosts(int e) is a thing
	 */
	@Override
	public void pressed(SelectionButton button) {
		for(int i = 0; i == machine.getNumberOfPopCanRacks(); i++)
		if (button == machine.getSelectionButton(1)) {
			productOrdered = i;
			accountant.checkCredit(productOrdered);	
		}
	}
	
	/**
	 * This is mostly scaffolding for future actions the SK might be responsible for like display updates
	 * also a minor security check, that made me feel safe and happy.
	 * 
	 * in the future we may implement a rejectedSale
	 * 
	 * if SB has the pop it should dispense it and call "productDelivered(int i)", back here
	 * 
	 * @param i = the pop the AC thinks we are selling
	 */
	public void approvedSale(int i) {
		if(i == productOrdered) {
			try {
				stockBoy.tryToDeliver(productOrdered);
			} catch (Exception e) {
				// As per current condition nothing needs to be done with these exceptions
				//but may be printed to display in the future
				e.printStackTrace();
			}
		//else
		}
	}
	
	/**
	 * again this is mostly here to plot out future updates
	 * @param i
	 */
	public void productDelivered(int i) {
		accountant.updateCredit(productOrdered);
	}

}




















