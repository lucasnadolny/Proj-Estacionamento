import java.io.Serializable;

public abstract class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String placa;
	private   String cor;
	protected String tipo;

	public Veiculo(String placa, String cor, String tipo) {
		this.placa = placa;
		this.cor = cor;
		}
	
	public String toString() {
		String retorno = "";
		retorno += "Placa: "     + this.placa     + "\n";
		retorno += "Cor: "    + this.cor    + " anos\n";
		retorno += "Tipo: " +this.tipo + "\n";
		return retorno;
	}
}