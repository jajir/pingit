package jirout.pingit;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class Ping {

    /**
     * From java implementation it works like this:
     *
     * Test whether that address is reachable. Best effort is made by the
     * implementation to try to reach the host, but firewalls and server
     * configuration may block requests resulting in a unreachable status while
     * some specific ports may be accessible. A typical implementation will use
     * ICMP ECHO REQUESTs if the privilege can be obtained, otherwise it will
     * try to establish a TCP connection on port 7 (Echo) of the destination
     * host.
     * 
     * 
     * 
     * 
     * @param host
     * @return
     */
    public void pingHost(final String host) {
        try (Socket socket = new Socket()) {
            final Period period = Period.start();
            InetAddress address = InetAddress.getByName(host);
            boolean reachable = address.isReachable(null, 0, 10_000);
            period.end();
            if (reachable) {
                System.out.println("response from " + host
                        + " was successfully returned in " + period.toDuration()
                        + " ms");
            } else {
                System.out.println("host " + host + " is not reachable.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out
                    .println("cannot resolve " + host + " :" + e.getMessage());
        }
    }

    public static void getURL(final String address) {
        try {
            final URL url = new URL("http://" + address);
            final HttpURLConnection urlConn = (HttpURLConnection) url
                    .openConnection();
            urlConn.setConnectTimeout(1000 * 10); // mTimeout is in seconds
            final long startTime = System.currentTimeMillis();
            urlConn.connect();
            final long endTime = System.currentTimeMillis();
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Time (ms) : " + (endTime - startTime));
                System.out.println("Ping to " + address + " was success");
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
