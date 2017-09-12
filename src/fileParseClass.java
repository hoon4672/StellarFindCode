import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class fileParseClass {

	HashMap<String, String> fileUrlMap;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("테스트 입니다. ");
		fileParseClass fpc = new fileParseClass();
		fpc.fileStream();
	}
	
	public void fileStream(){
		fileUrlMap = new HashMap<String, String>();
		
				
		try {
			addFile();
			String oldFileName = "";
			//File file = new File("D:\\문서\\Stellar_TestCASE.doc");
			File file = new File("D:\\문서\\Stellar_TestCASE.txt");
			
			if(file.exists()){
				file.delete();
			}
			//오름차순
	        TreeMap<String, String> treeMap = new TreeMap<String, String>( fileUrlMap );
			Iterator<String> iteratorKey = treeMap.keySet( ).iterator( ); 
			
			while(iteratorKey.hasNext()){
				String key = iteratorKey.next();
				
	            BufferedReader bfRIn = new BufferedReader(new FileReader(treeMap.get(key).toString()));
	            //FileWriter fileWrite = new FileWriter(file,true);	
	            BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
	            
	            
	            String directoryNameIndex = new String(key);
	            int directoryIndex = directoryNameIndex.lastIndexOf("_");
	            String directoryName= directoryNameIndex.substring(0,directoryIndex );
	            
	            String fileName = new String(treeMap.get(key));
	            int fileIndex = fileName.lastIndexOf(File.separator);
	            
	           // System.out.println( String.format("키 : %s, 값 : %s", directoryName, treeMap.get(key)) );
	            
            	if(oldFileName.equals(directoryName)){
            		//System.out.println("OLD DirectoryName    : "+oldFileName +"     Now DirectoryName : "+key.toString()+"\n");
            	}else{   
            		if(oldFileName == ""){
            			
            		}else{
            			out.newLine();
            			out.newLine();            			
            		}
            		out.write("###### Directory Name : "+directoryName+" ######");
            		oldFileName = directoryName;
            	}
            	
            	out.newLine();	
            	out.write("*** File Name : "+fileName.substring(fileIndex+1, fileName.length())+"***");
            	out.newLine();
	            
	            
            	boolean lineEndB = false;
            	String fileStr;	            
	            String lineAddStr = "";
	            int lineEndIndex = 0;
	            int lineNumber  = 1;
	            while ((fileStr = bfRIn.readLine()) != null) {
	            	
	            	if(lineEndB == true){
	            		
                		if(fileStr.endsWith(")")){
                			int lastIndexOfStr = lineAddStr.lastIndexOf("\"");
                			int newIndexOfStr = fileStr.indexOf("\"");
    	                	out.write("Line Number : "+lineEndIndex+"  "+lineAddStr.substring(0, (lastIndexOfStr > 0) ? lastIndexOfStr : lineAddStr.length())+" "+fileStr.substring((newIndexOfStr > 0) ? newIndexOfStr : 0,fileStr.length()));
    	                	//out.write("Line Number : "+lineEndIndex+"  \""+lineAddStr.replaceAll("\"", "")+" "+newFileStr.substring(1,newFileStr.length()));
                    		out.newLine();
                			lineEndB 		= false;
            	            lineAddStr 		= "";
            	            lineEndIndex 	= 0;	                		
                		}else{
                			lineAddStr += fileStr.replaceAll("\"", "");
                			
                		}
                		
	                }
	            	
	               // System.out.println(fileStr);
	                if(fileStr.contains("TEST_CASE")){
	                	if(fileStr.endsWith(")"))	                	{
	                		out.write("Line Number : "+lineNumber+"  "+fileStr);
	                		out.newLine();
	                	}else{
	                		lineEndIndex = lineNumber;
	                		lineAddStr += fileStr;
	                		lineEndB = true;
	                	}	                
	                }
	                
	                if(fileStr.contains("TEST_CASE_METHOD")){
	                	if(fileStr.endsWith(")"))
	                	{
		                	out.write("Line Number : "+lineNumber+"  "+fileStr);
		                	out.newLine();
	                	}else {
	                		lineEndIndex = lineNumber;
	                		lineAddStr += fileStr;
	                		lineEndB = true;
	                	}
	                }
	                
	                if(fileStr.contains("SECTION")){
	                	if(fileStr.endsWith(")")){
	                	out.write("Line Number : "+lineNumber+"  "+fileStr);
	                	out.newLine();
		                }else {
	                		lineEndIndex = lineNumber;
	                		lineAddStr += fileStr;
	                		lineEndB = true;
	                	}
	                }
	                
	                
	                
	                lineNumber++;
	            }
	            out.close();
	            bfRIn.close();
	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<String, String> pushMap(String directoryName, String url){
		if(directoryName.equals(null)  || url.equals(null) || directoryName.equals("")  || url.equals("")){
			return fileUrlMap;
		}
		fileUrlMap.put(directoryName, url);
		return fileUrlMap;
	}
	
	public void addFile(){
		pushMap("transactions_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\AllowTrustTests.cpp");
		pushMap("transactions_2","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\ChangeTrustTests.cpp");
		pushMap("transactions_3","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\InflationTests.cpp");
		pushMap("transactions_4","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\MergeTests.cpp");
		pushMap("transactions_5","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\OfferTests.cpp");
		pushMap("transactions_6","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\PaymentTests.cpp");
		pushMap("transactions_7","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\SetOptionsTests.cpp");
		pushMap("transactions_8","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\SignatureUtilsTest.cpp");
		pushMap("transactions_9","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\TxEnvelopeTests.cpp");
		pushMap("transactions_10","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\transactions\\TxResultsTests.cpp");
		pushMap("test_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\test.cpp");
		pushMap("test_2","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\test.h");
		pushMap("test_3","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TestAccount.cpp");
		pushMap("test_4","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TestAccount.h");
		pushMap("test_5","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TestExceptions.cpp");
		pushMap("test_6","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TestExceptions.h");
		pushMap("test_7","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TestPrinter.cpp");
		pushMap("test_8","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TestPrinter.h");
		pushMap("test_9","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TestUtils.cpp");
		pushMap("test_10","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TestUtils.h");
		pushMap("test_11","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TxTests.cpp");
		pushMap("test_12","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\test\\TxTests.h");
		pushMap("simulation_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\simulation\\CoreTests.cpp");
		pushMap("scp_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\scp\\QuorumSetTests.cpp");
		pushMap("scp_2","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\scp\\SCPTests.cpp");
		pushMap("scp_3","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\scp\\SCPUnitTests.cpp");
		pushMap("process_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\process\\ProcessTests.cpp");
		pushMap("overlay_2","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\overlay\\FloodTests.cpp");
		pushMap("overlay_3","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\overlay\\ItemFetcherTests.cpp");
		pushMap("overlay_4","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\overlay\\OverlayManagerTests.cpp");
		pushMap("overlay_5","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\overlay\\OverlayTests.cpp");
		pushMap("overlay_6","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\overlay\\TCPPeerTests.cpp");
		pushMap("overlay_7","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\overlay\\TrackerTests.cpp");
		pushMap("main_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\main\\ApplicationTests.cpp");
		pushMap("main_2","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\main\\ConfigTests.cpp");
		pushMap("main_3","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\main\\LruCacheTests.cpp");
		pushMap("ledger_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\ledger\\LedgerDeltaTests.cpp");
		pushMap("ledger_2","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\ledger\\LedgerEntryTests.cpp");
		pushMap("ledger_3","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\ledger\\LedgerHeaderTests.cpp");
		pushMap("ledger_4","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\ledger\\LedgerPerformanceTests.cpp");
		pushMap("ledger_5","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\ledger\\LedgerTests.cpp");
		pushMap("ledger_6","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\ledger\\LedgerTestUtils.cpp");
		pushMap("ledger_7","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\ledger\\LedgerTestUtils.h");
		pushMap("ledger_8","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\ledger\\SyncingLedgerChainTests.cpp");
		pushMap("history_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\history\\HistoryTests.cpp");
		pushMap("history_2","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\history\\InferredQuorumTests.cpp");
		pushMap("herder_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\herder\\HerderTests.cpp");
		pushMap("herder_2","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\herder\\PendingEnvelopesTests.cpp");
		pushMap("herder_3","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\herder\\ProtocolUpgradeTests.cpp");
		pushMap("database_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\database\\DatabaseTests.cpp");
		pushMap("crypto_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\crypto\\CryptoTests.cpp");
		pushMap("bucket_1","D:\\Repositories\\GitRepository\\Dev_Core\\stellar-core\\src\\bucket\\BucketTests.cpp");
	}

}
