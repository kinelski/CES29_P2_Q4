package exe4;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VulnerableClassTest {

	private VulnerableClass vul = null;
	private BufferedWriter bw = null;
	
	private void createFile(String filename, String text){
		try{
			bw = new BufferedWriter(new FileWriter(filename));
			bw.write(text);
			bw.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void setarInput(String input){
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	}
	
	private void deleteFile(String filename){
		try{
			File file = new File(filename);
			file.delete();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Before
	public void setup(){
		vul = new VulnerableClass();
	}
	
	@Test
	public void arquivosComNomesInvalidosGeramExcecao () {
		try{
			vul.vulnerableMethod("ser ou nao ser eis a questao"); // " " -> caractere invalido
			fail("Esperado: Exception.class");
		}
		catch(Exception e){}
	}
	
	@Test
	public void arquivoComCaractereInvalidoGeraExcecao(){
		createFile("arq1.txt", "armando?"); // ? -> caractere invalido
		setarInput("R");
		
		try{
			vul.vulnerableMethod("arq1.txt");
			fail("Esperado: Exception.class");
		}
		catch(Exception e){}
		
		deleteFile("arq1.txt");
	}
	
	@Test
	public void tentarEscreverCaractereInvalidoEmArquivoGeraExcecao(){
		createFile("arq2.txt", "soma");
		setarInput("W\r\n" +
					"Creio que nao!"); // ! -> caractere invalido
		
		try{
			vul.vulnerableMethod("arq2.txt");
			fail("Esperado: Exception.class");
		}
		catch(Exception e){}
		
		deleteFile("arq2.txt");
	}
	
	@Test
	public void tentarInserirComandoInvalidoGeraExcecao(){
		createFile("arq3.txt", "pa");
		setarInput("X");
		
		try{
			vul.vulnerableMethod("arq3.txt");
			fail("Esperado: Exception.class");
		}
		catch(Exception e){}
		
		setarInput("Washington");
		
		try{
			vul.vulnerableMethod("arq3.txt");
			fail("Esperado: Exception.class");
		}
		catch(Exception e){}
		
		deleteFile("arq3.txt");
	}
	
	@After
	public void cleanup(){
		System.setIn(System.in);
		System.setOut(System.out);
		
		try{
			if (bw != null)
				bw.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
