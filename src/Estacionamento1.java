import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Estacionamento1 {

	private ArrayList<Veiculo> veiculos;


	public Estacionamento1( ) {
		this.veiculos = new ArrayList<Veiculo>();
	}

	public void adicionaVeiculo(Veiculo mani) {
		this.veiculos.add(mani);
	}

	public void listarVeiculos() {
		for(Veiculo mani:veiculos) {
			System.out.println(mani.toString());
		}
		System.out.println("Total = " + this.veiculos.size() + " veículos listados com sucesso!\n");
	}
	
	public void excluirVeiculo(Veiculo mani) {
		if (this.veiculos.contains(mani)) {
			this.veiculos.remove(mani);
			System.out.println("[Veiculo " + mani.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Veículo inexistente!\n");
	}

	public void excluirVeiculos() {
		veiculos.clear();
		System.out.println("Veículos excluidos com sucesso!\n");
	}
	public void gravarVeiculos()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\veiculos.dat"));
			for(Veiculo mani:veiculos) {
				outputStream.writeObject(mani);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarVeiculos() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\veiculos.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Carro)  
					this.veiculos.addAll((Carro)obj);
				else if (obj instanceof Moto)  
					this.veiculos.addAll((Moto)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Veículos recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		Estacionamento1 est  = new Estacionamento1();

	}

}