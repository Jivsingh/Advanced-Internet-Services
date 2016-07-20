import org.xbill.DNS.*;

class adns {
	
	private void resolve (int type, String domain) {
		Record[] records;
		try {
			records = new Lookup(domain, type).run();
		
		System.out.println(";; QUESTION SECTION:");
		System.out.println(domain+"\tIN\t"+Type.string(type));
		System.out.println(";; ANSWER SECTION:");
		System.out.println("DNS Records received =");
		for (int i = 0; i < records.length; i++) {
//			MXRecord mx = (MXRecord) records[i];
			System.out.println(records[i].toString());
		}
		
		//For doing any Record Type specific processing, not needed here
		switch (type) {
		case Type.A:
			/*
			Record [] records = new Lookup(domain, Type.value(type)).run();
			for (int i = 0; i < records.length; i++) {
				ARecord mx = (ARecord) records[i];
				System.out.println("Record received = \n"+records[i].toString());
			}
			*/
			break;
		
		case Type.CNAME:
			
			break;
		
		case Type.SRV:
			
			break;	

		default:
			System.out.println("ERROR: DNS record type "+type+" is not supported");
			System.out.println("Exiting..");
			break;
		}
		} catch (TextParseException e) {
			System.out.println("Unable to parse text!");
			e.printStackTrace();
		}
		
	}
	
	adns (String type, String domain) {
		int val = Type.value(type);
		if (val < 0) {
			System.out.println("DNS type "+type+" is not supported!");
		} else {
			resolve(val, domain);
		}
	}
	
	public static void main (String args[]) throws TextParseException {
		String type = null, domain = null;
		if(args.length < 2) {
			System.out.println("Invalid number of arguments!");
			System.out.println("Usage: java adns <type> <domain>");
		} else {
			type = args[0];
			domain = args[1];
			new adns(type, domain);
		}

	}
	
}