import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.jsoup.Jsoup;


public class SupportFromJava {

	public static String extractText(String emailBody) {
		  StringReader reader = new StringReader(emailBody);
		     StringBuilder sb = new StringBuilder();
		     BufferedReader br = new BufferedReader(reader);
		     String line;
		     try {
				while ( (line=br.readLine()) != null) {
				   sb.append(line);
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     String textOnly = Jsoup.parse(sb.toString()).text();
		     return textOnly;
		   }
	
	public ArrayList<String> verifyMailBackEnd(final String emailAddress, String emailSubject) {
		Store store = null;
		try {
			Thread.sleep(25000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Properties props = System.getProperties();
		props.put("mail.imaps.auth.plain.disable", "true");

		ArrayList<String> finalEmailValues=new ArrayList<String>();

		try {
			Session session = Session.getInstance(props, null);
			session.setDebug(true);
			 store = session.getStore("imaps");

			store.connect("outlook.office365.com", 993,"devappbyphone@prosper.com","D1al1t1n" );

			System.out.println(store);
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);

			// Get the last 30 messages
			final int MAX_MESSAGES=30;
			int end = inbox.getMessageCount();
			int start = end - MAX_MESSAGES + 1;
			Message messages[] = inbox.getMessages(start, end);

			// Reverse the ordering so that the latest comes out first
			Message messageReverse[] = reverseMessageOrder(messages);
			
			/*// search for all "unseen" messages
		    Flags seen = new Flags(Flags.Flag.SEEN);
		    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
		    Message messages[] = inbox.search(unseenFlagTerm);*/

		 

			Main:
				for (Message message : messageReverse) {
					Address[] a;
					int j;
					if ((a = message.getRecipients(Message.RecipientType.TO)) != null) {
						for (j = 0; j < a.length; j++){
							System.out.println("Recepient Id------------------------"+a[j].toString());
							System.out.println("Expected Id------------------------"+emailAddress);
							if(a[j].toString().equalsIgnoreCase(emailAddress) && message.getSubject().contains(emailSubject)){
								finalEmailValues= writePart(message);
								break Main;
							}
						}
					} 
				}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(2);
		}finally{
			try {
				store.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return finalEmailValues;

	}
	private static Message[] reverseMessageOrder(Message[] messages) {
		Message revMessages[]= new Message[messages.length];
		int i=messages.length-1;
		for (int j=0;j<messages.length;j++,i--) {
			revMessages[j] = messages[i];

		}

		return revMessages;

	}

	public String getHrefFromEmailBody(String emailBody) {
		String href = null;
		Pattern p = Pattern.compile("href=\"(.*?)\"", Pattern.DOTALL);
		Matcher m = p.matcher(emailBody);

		while (m.find()) 
		{
			System.out.println(m.group(1));
			if(m.group(1).contains("https://www.stg.circleone.com/")){
				href=m.group(1).replaceAll("amp;", "");
			}else if(m.group(1).contains("http://www.qa32.c1.dev/")){
				href=m.group(1).replaceAll("amp;", "");
			}else if (m.group(1).contains("http://www.qa34.c1.dev/")){
				href=m.group(1).replaceAll("amp;", "");
			}
			else if(m.group(1).contains("http://www.qa34.c1.dev/")){
				href=m.group(1).replaceAll("amp;", "");
			}
		}
		return href;
	}

	/*
	 * This method would return FROM,TO and SUBJECT of the message
	 */
	public static ArrayList<String> writeEnvelope(Message m) throws Exception {
		System.out.println("This is the message envelope");
		System.out.println("---------------------------");
		Address[] a;
		ArrayList<String> returnValues= new ArrayList<String>();


		// FROM
		if ((a = m.getFrom()) != null) {
			int j;
			for (j = 0; j < a.length; j++){
				System.out.println("FROM: " + a[j].toString());
				returnValues.add(a[j].toString());
			}
		}

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++){
				System.out.println("TO: " + a[j].toString());
				returnValues.add(a[j].toString());
			}
		}
		// SUBJECT
		if (m.getSubject() != null){
			System.out.println("SUBJECT: " + m.getSubject());
			returnValues.add(m.getSubject());
		}

		return returnValues;
	}


	/*
	 * This method checks for content-type 
	 * based on which, it processes and
	 * fetches the content of the message
	 */
	public static ArrayList<String> writePart(Part p) throws Exception {

		ArrayList<String> returnFromToSubject=new ArrayList<String>();

		if (p instanceof Message){
			//Call method writeEnvelope
			returnFromToSubject=writeEnvelope((Message) p);
			System.out.println("----------------------------");
			System.out.println("CONTENT-TYPE: " + p.getContentType());
		}
		//check if the content is plain text
		if (p.isMimeType("text/plain")) {
			System.out.println("This is plain text");
			System.out.println("---------------------------");
			System.out.println((String) p.getContent());
			returnFromToSubject.add((String) p.getContent());
		} 
		//check if the content has attachment
		else if (p.isMimeType("multipart/*")) {
			System.out.println("This is a Multipart");
			System.out.println("---------------------------");
			Multipart mp = (Multipart) p.getContent();
			int count = mp.getCount();
			for (int i = 0; i < count; i++)
				writePart(mp.getBodyPart(i));
		} 
		//check if the content is a nested message
		else if (p.isMimeType("message/rfc822")) {
			System.out.println("This is a Nested Message");
			System.out.println("---------------------------");
			writePart((Part) p.getContent());
		} 
		//check if the content is an inline image
		else if (p.isMimeType("image/jpeg")) {
			System.out.println("--------> image/jpeg");
			Object o = p.getContent();

			InputStream x = (InputStream) o;
			// Construct the required byte array
			System.out.println("x.length = " + x.available());
			byte[] bArray = null;
			int i;
			while ((i = (int) ((InputStream) x).available()) > 0) {
				int result = (int) (((InputStream) x).read(bArray));
				if (result == -1)
					i = 0;
				bArray = new byte[x.available()];

				break;
			}
			FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
			f2.write(bArray);
		} 
		else if (p.getContentType().contains("image/")) {
			System.out.println("content type" + p.getContentType());
			File f = new File("image" + new Date().getTime() + ".jpg");
			DataOutputStream output = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(f)));
			com.sun.mail.util.BASE64DecoderStream test = 
					(com.sun.mail.util.BASE64DecoderStream) p
					.getContent();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = test.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		} 
		else {
			Object o = p.getContent();
			if (o instanceof String) {
				System.out.println("This is a string");
				System.out.println("---------------------------");
				System.out.println((String) o);
				returnFromToSubject.add((String) o);
			} 
			else if (o instanceof InputStream) {
				System.out.println("This is just an input stream");
				System.out.println("---------------------------");
				InputStream is = (InputStream) o;
				is = (InputStream) o;
				int c;
				while ((c = is.read()) != -1)
					System.out.write(c);
			} 
			else {
				System.out.println("This is an unknown type");
				System.out.println("---------------------------");
				System.out.println(o.toString());
			}
		}

		return returnFromToSubject;

	}
}
