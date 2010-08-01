import netapp.manage.*;

public class NetAppHello {
	public static void main(String[] args) {
		NaElement xi;
		NaElement xo;
		NaServer  s;

		/*if ( args.length < 3 ) {
			System.err.println("Usage: hello_ontapi  filer user passwd");
			System.exit(1);
		}*/
		try {
			//Initialize connection to server, and
			//request version 1.3 of the API set
			//	
			s = new NaServer("192.168.1.112",1,3);
			s.setStyle(NaServer.STYLE_LOGIN_PASSWORD);
			s.setAdminUser("root", "S1o2u3L4c5");
			
			//Invokes ONTAPI API to get the ONTAP 
			//version number of a filer		
			xi = new NaElement("system-get-version");
			xo = s.invokeElem(xi);
			System.out.print("Hello!  Filer version is ");
			System.out.println(xo.getChildContent("version"));
			s.close();
		}
		catch (Exception e) {
			System.err.println(e.toString());
			System.exit(1);
		}
	}
} 