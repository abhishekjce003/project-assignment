package com.morgan.assignment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.morgan.approval.task.DivisionHeadApprovalTask;
import com.morgan.approval.task.FundManagerApprovalTask;
import com.morgan.approval.task.OperationsApprovalTask;
import com.morgan.approval.task.ResearchAnalystApprovalTask;
import com.morgan.rule.constants.RuleConstants;

public class ProcessingEngine {
	
	public static void processApprovalForFundRequest(String rule, FundRequest request){
		ApprovalEntity entity = new ApprovalEntity(request);
		ExecutorService es = Executors.newFixedThreadPool(4);
		CountDownLatch cdl;
		List<Callable<Boolean>> taskList = new ArrayList<Callable<Boolean>>();
		String[] jobs = rule.split(RuleConstants.then);
		for(int i=0;i<jobs.length;i++){
			String[] approvals = jobs[i].split(RuleConstants.paralle_with);
			cdl = new CountDownLatch(approvals.length);
			ArrayList<String> parallelApprovals = new ArrayList<String>(Arrays.asList(approvals));
			
			if(parallelApprovals.contains(RuleConstants.RESEARCH_ANALYST_APPROVAL)){
				taskList.add(new ResearchAnalystApprovalTask(entity,cdl));
			}if(parallelApprovals.contains(RuleConstants.FUND_MANAGER_APPROVAL)){
				taskList.add(new FundManagerApprovalTask(entity,cdl));
			}if(parallelApprovals.contains(RuleConstants.DIVISION_HEAD_APPROVAL)){
				taskList.add(new DivisionHeadApprovalTask(entity,cdl));
			}if(parallelApprovals.contains(RuleConstants.OPERATIONS_APPROVAL)){
				taskList.add(new OperationsApprovalTask(entity,cdl));
			}
			try {
				ArrayList<Future<Boolean>> results= (ArrayList<Future<Boolean>>) es.invokeAll(taskList);
				Iterator <Future<Boolean>> itr = results.iterator();
				while(itr.hasNext()){
					boolean appr = itr.next().get();
					//we can copy future objects to separate arraylist, and later on we can check the contents to decide
					//whether to release funds or not
					if(appr)
						System.out.println("!");
				}
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted: "+e.getMessage());
			}catch (ExecutionException e1) {
				System.out.println("Execution exception: "+e1.getMessage());
			}
			taskList.clear();
		}
		es.shutdown();
		//we can check results for approvals and accordingly release funds
	}
	
	public static void main(String[] args){
		FundRequest req = new FundRequest(1);
		//the rule is configurable with parallel approval separated by #
		//and sequential approval(s) separated by comma
		String rule = RuleConstants.RESEARCH_ANALYST_APPROVAL+RuleConstants.paralle_with+RuleConstants.FUND_MANAGER_APPROVAL+RuleConstants.then+
						RuleConstants.DIVISION_HEAD_APPROVAL+RuleConstants.then+
						RuleConstants.OPERATIONS_APPROVAL;
		ProcessingEngine.processApprovalForFundRequest(rule, req);
	}
}
