package com.morgan.approval.task;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.morgan.assignment.ApprovalEntity;

public class DivisionHeadApprovalTask implements Callable<Boolean>{
	ApprovalEntity entity;
	boolean approval;
	CountDownLatch latch;
	
	public DivisionHeadApprovalTask(ApprovalEntity entity, CountDownLatch latch){
		this.entity = entity;
		this.latch = latch;
	}
	
	public Boolean call(){
		approval = entity.divisionHeadApproval();
		latch.countDown();
		return approval;
	}
}
