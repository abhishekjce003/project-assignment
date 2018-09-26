package com.morgan.approval.task;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.morgan.assignment.ApprovalEntity;

public class FundManagerApprovalTask implements Callable<Boolean> {
	ApprovalEntity entity;
	boolean approval;
	CountDownLatch latch;
	
	public FundManagerApprovalTask(ApprovalEntity entity,CountDownLatch latch){
		this.entity = entity;
		this.latch = latch;
	}
	
	public Boolean call(){
		approval = entity.fundManagerApproval();
		latch.countDown();
		return approval;
	}
}
