package org.bars.demohazelcast;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@EnableCaching
public class DemoHazelcastApplication {

	public static interface CityService {
		public String getCity();
	}
	
	@Bean
	public HazelcastInstance getInstance(){
		return Hazelcast.newHazelcastInstance();
	}
	@Bean
	public CityService cityService(){
		return new CityService(){

			@Override
			@Cacheable("city")
			public String getCity() {
				// TODO Auto-generated method stub
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "Chennai";
			}
			
		};
	}
	
	@Component
	public static class myCommandlineRunner implements CommandLineRunner{
		
		@Autowired
		CityService cityService;
		
		@Override
		public void run(String... arg0) throws Exception {
			// TODO Auto-generated method stub
			long start = System.nanoTime();
			System.out.println(cityService.getCity());
			System.out.println("Start time"+ start+"End time"+ System.nanoTime());
			start = System.nanoTime();
			System.out.println(cityService.getCity());
			System.out.println("Start time"+ start+"End time"+ System.nanoTime());
			start = System.nanoTime();
			System.out.println(cityService.getCity());
			System.out.println("Start time"+ start+"End time"+ System.nanoTime());
			
		}
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoHazelcastApplication.class, args);
		System.out.println("Hello");
	}
}
