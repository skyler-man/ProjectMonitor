package com.taocares.monitor;

import com.taocares.monitor.service.IJenkinsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitorApplicationTests {

	@Autowired
	private IJenkinsService iJenkinsService;

	@Test
	public void contextLoads() throws IOException, URISyntaxException {
		iJenkinsService.getJenkinsInfoForSchedule();
	}

}
