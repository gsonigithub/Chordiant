package com.vodacom.chordiant.reporting.mvc.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vodacom.chordiant.reporting.ReportingMainApplication;

import dm.com.vodafone.dm.ws.ProductArray;
import dm.com.vodafone.dm.ws.Request;
import dm.vodafone.com.DecisionControllerWS_Service;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReportingMainApplication.class)
public class DecisionControllerWSTest {

	private String MSISDN = "0721015394";
	private String srcSystem = "PPFE";

	//qcho001zafsrh.vodacom.corp
	String urlPart1 = "http://qcho00";
	String zatUrlPart2 = "zafsrh.vodacom.corp:703";
	String zafUrlPart2 = "zafsrh.vodacom.corp:703";
	String urlPart3 = "/rtds/webservices-adapter/DecisionControllerWS?WSDL";

	@Test
	public void testConnect() throws MalformedURLException {
//= serverNum - 2
		for (int serverNum =1; serverNum < 2; serverNum++ ) {
			for (int portNum = 1; portNum < 3; portNum++) {
				
				System.out.println("Server Num:pcho00" + serverNum + " Port Num:703" + portNum);
				
				String url = urlPart1;
				if (serverNum % 2 == 0) {
					url = url + serverNum + zafUrlPart2 + portNum + urlPart3;
				} else {
					url = url + serverNum + zatUrlPart2 + portNum + urlPart3;
				}

				DecisionControllerWS_Service service = new DecisionControllerWS_Service(
						new URL(url));

				Request request = new Request();
				request.setMSISDN(MSISDN);
				request.setSourceSystem(srcSystem);

				ProductArray array = service.getDecisionControllerWS()
						.getOffers(request);
				
				System.out.println("Num of offers:" + array.getProducts().size());

				

			}
		}

	}
	
}
