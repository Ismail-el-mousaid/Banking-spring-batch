package org.id.bankingbatch.batch.launcher;

import java.util.HashMap;
import java.util.Map;

import org.id.bankingbatch.batch.processor.BankTransactionAnalyticsProcessor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*Le Job sera declanché à chaque fois qu'on envoie une requete GET via ce path*/

@RestController
public class JobRestController {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;
	@Autowired
	private BankTransactionAnalyticsProcessor analyticsProcessor;
	
	@GetMapping("/startJob")
	public BatchStatus load() throws Exception{
		//Spécifier les params
		Map<String, JobParameter> params = new HashMap<>();
		params.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(params);
		//Exécution du job
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		while(jobExecution.isRunning()){
			System.out.println("Le batch est entrain de l'exécution");
		}
		//A la fin de l'execution, retourner le status de l'exécution du job
		return jobExecution.getStatus();
	}

	@GetMapping("/analytics")
	public Map<String, Double> analytics(){
		Map<String, Double> map = new HashMap<>();
		map.put("totalCredit", analyticsProcessor.getTotalCredit());
		map.put("totalDebit", analyticsProcessor.getTotalDebit());
		return map;
	}
	
}
