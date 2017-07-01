package exe4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VulnerableClass {
	public void vulnerableMethod(String FILENAME) throws Exception{
		// Um bom pattern para nome de arquivos
		Pattern pattern = Pattern.compile("[^A-Za-z0-9._]");
		Matcher matcher = pattern.matcher(FILENAME);
		
		if (matcher.find()){
			throw new Exception();
		}
		
		Scanner console = new Scanner(System.in);
		
	    System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, "
	    		+ "W para escrever em um arquivo>? ");
	    
	    String opr= console.nextLine();
		
	    if (opr.equals("R")){
			try {
				String sCurrentLine;

				BufferedReader br = null;
				br = new BufferedReader(new FileReader(FILENAME));

				while ((sCurrentLine = br.readLine()) != null) {
					// Apenas a nivel de ilustracao: existem muitos outros caracteres
					// validos para o nosso output. Acabei optando por utilizar esta
					// convencao a fim de facilitar
					pattern = Pattern.compile("[^A-Za-z0-9._ ]");
					matcher = pattern.matcher(sCurrentLine);
					
					if (!matcher.find())
						System.out.println(sCurrentLine);
					else{
						br.close();
						throw new Exception();
					}
				}
				
				br.close();

			} catch (IOException e) {

				e.printStackTrace();

			} 
		}
		
		else if (opr.equals("W")){
			try {
				
				BufferedWriter buffWrite = null;
				buffWrite = new BufferedWriter(new FileWriter(FILENAME));
				
				String linha = "";
				System.out.println("Escreva algo: ");
			    linha = console.nextLine();
			    
			    // Novamente, um pattern arbitrario, apenas para fins
			    // de ilustracao
			    pattern = Pattern.compile("[^A-Za-z0-9._ ]");
				matcher = pattern.matcher(linha);
				
				if (!matcher.find()){
					buffWrite.append(linha + "\n"); 
			    	buffWrite.close();
				}else{
					buffWrite.close();
					throw new Exception();
				}
		    
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	    
		else{
			console.close();
			throw new Exception();
		}
	    
	    console.close();
	}
}
