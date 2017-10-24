package com.vodacom.chordiant.reporting.data.repository;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import com.vodacom.chordiant.reporting.mvc.view.PortMonitorView;

@Component
public class PortStatusRepository {

	public List<PortMonitorView> getPortStatus(String serverListAsString) {
		
		//String[] serverList = serverListAsString.split(",");
		
		String[] serverList = StringUtils.split(serverListAsString, ",");
		
		List<PortMonitorView> portMonitor =new ArrayList<PortMonitorView>();
		
		for (String server : serverList){
			
			portMonitor.add(checkSocket(server));
        }
		
		return portMonitor;
	}

	private PortMonitorView checkSocket(String node) {
		
		PortMonitorView portMonitor = new PortMonitorView();
		portMonitor.setHost(node);
		portMonitor.setP7031(getPortAvailability(node,7031));
		portMonitor.setP7032(getPortAvailability(node,7032));
		portMonitor.setP7033(getPortAvailability(node,7033));
		portMonitor.setP7034(getPortAvailability(node,7034));
		portMonitor.setP7035(getPortAvailability(node,7035));
		portMonitor.setP7036(getPortAvailability(node,7036));
		portMonitor.setP7037(getPortAvailability(node,7037));
		portMonitor.setP7038(getPortAvailability(node,7038));
		portMonitor.setP7039(getPortAvailability(node,7039));
		return portMonitor;
	}
	
	private boolean getPortAvailability(String node, int port){
		
		boolean result = false ;
        Socket s = null;
        String reason = null ;
        try {
            s = new Socket();
            s.setReuseAddress(true);
            SocketAddress sa = new InetSocketAddress(node, port);
            s.connect(sa, 5000);
        } catch (IOException e) {
            if ( e.getMessage().equals("Connection refused")) {
                reason = "port " + port + " on " + node + " is closed.";
            };
            if ( e instanceof UnknownHostException ) {
                reason = "node " + node + " is unresolved.";
            }
            if ( e instanceof SocketTimeoutException ) {
                reason = "timeout while attempting to reach node " + node + " on port " + port;
            }
        } finally {
            if (s != null) {
                if ( s.isConnected()) {
                    //System.out.println("Port " + port + " on " + node + " is reachable!");
                    result = true;
                } else {
                    System.out.println("Port " + port + " on " + node + " is not reachable; reason: " + reason );
                    result = false;
                }
                try {
                    s.close();
                } catch (IOException e) {
                }
            }
        }
		return result;
	}
}
