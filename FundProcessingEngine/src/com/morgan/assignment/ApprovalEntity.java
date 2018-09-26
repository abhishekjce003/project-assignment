package com.morgan.assignment;

public class ApprovalEntity {
	private FundRequest req;
	
	public ApprovalEntity(FundRequest req) {
		this.setReq(req);
	}
	
	public boolean researchAnalystApproval(){
		//process on the req attribute
		System.out.println("Started approval process from Research Analyst");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Got approval from Research Analyst");
		return true;
	}
	
	public boolean fundManagerApproval(){
		//process on the req attribute
		System.out.println("Started approval process from Fund Manager");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Got approval from Fund Manager");
		return true;
	}
	public boolean divisionHeadApproval(){
		//process on the req attribute
		System.out.println("Started approval process from Division Head");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Got approval from Division Head");
		return true;
	}
	public boolean operationsApproval(){
		//process on the req attribute
		System.out.println("Started approval process from Operations");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Got approval from Opeartions");
		return true;
	}

	public FundRequest getReq() {
		return req;
	}

	public void setReq(FundRequest req) {
		this.req = req;
	}
}
