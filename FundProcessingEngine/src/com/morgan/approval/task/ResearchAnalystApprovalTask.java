package com.morgan.approval.task;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.morgan.assignment.ApprovalEntity;

public class ResearchAnalystApprovalTask implements Callable<Boolean>{
	ApprovalEntity entity;
	boolean approval;
	CountDownLatch latch;
	
	public ResearchAnalystApprovalTask(ApprovalEntity entity, CountDownLatch latch){
		this.entity = entity;
		this.latch = latch;
	}
	
	public Boolean call(){
		approval = entity.researchAnalystApproval();
		latch.countDown();
		return approval;
	}

}
