import sun.net.spi.nameservice.NameService;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.net.InetAddress;

public class DNSTest
{
    public static void main(String[] args) throws Exception
    {
        NameService dns = new DNSNameService();
        InetAddress [] result = dns.lookupAllHostAddr("google.com");
        for (InetAddress inetAddress : result)
        {
            System.out.println(inetAddress);
        }
        for (InetAddress inetAddress : result)
        {
            System.out.println(inetAddress);
            System.out.println(" " + dns.getHostByAddr(inetAddress.getAddress()));
        }
    }
}
