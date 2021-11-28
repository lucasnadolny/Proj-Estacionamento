import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Estacionamento {
	private ArrayList<Veiculo> veiculos;

	public Estacionamento() {
		this.veiculos = new ArrayList<Veiculo>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Carro leCarro(){

		String [] valores = new String [3];
		String [] nomeVal = {"Placa", "Cor", "Tipo"};
		valores = leValores (nomeVal);

		
	}

	public Moto leMoto (){

		String [] valores = new String [3];
		String [] nomeVal = {"Placa", "Cor", "Tipo"};
		valores = leValores (nomeVal);

		
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // Método estático, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // Não conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numint;
		
		//Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaVeiculos (ArrayList<Veiculo> veiculos){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\estacionamento.dados"));
			for (int i=0; i < veiculos.size(); i++)
				outputStream.writeObject(veiculos.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Veiculo> recuperaVeiculos (){
		ArrayList<Veiculo> veiculosTemp = new ArrayList<Veiculo>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\veiculos.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Veiculo) {
					veiculosTemp.add((Veiculo) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com veículos NÃO existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return veiculosTemp;
		}
	}

	public void menuEstacionamento (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Estacionamento\n" +
					"Opções:\n" + 
					"1. Entrar Veículos\n" +
					"2. Exibir Veículos\n" +
					"3. Limpar Veículos\n" +
					"4. Gravar Veículos\n" +
					"5. Recuperar Veículos\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de veículos\n" +
						"Opções:\n" + 
						"1. Carro\n" +
						"2. Moto\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: veiculos.add((Veiculo)leCarro());
				break;
				case 2: veiculos.add((Veiculo)leMoto());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Veículo para entrada NÃO escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (veiculos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com veículos primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < veiculos.size(); i++)	{
					dados += veiculos.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (veiculos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com veículos primeiramente");
					break;
				}
				veiculos.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (veiculos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com veículos primeiramente");
					break;
				}
				salvaVeiculos(veiculos);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				veiculos = recuperaVeiculos();
				if (veiculos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo ESTACIONAMENTO");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main(String [] args){
		
		Estacionamento est = new Estacionamento ();
		est.menuEstacionamento();

	}

}