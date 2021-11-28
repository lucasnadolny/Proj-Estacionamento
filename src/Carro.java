public class Carro extends Veiculo {

	private static final long serialVersionUID = 1L;

	public Carro(String placa, String cor, String tipo) {
		super(placa, cor, tipo);
		this.tipo = "Carro";
	}
}
