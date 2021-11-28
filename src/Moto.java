public class Moto extends Veiculo {

	private static final long serialVersionUID = 1L;

	public Moto(String placa, String cor, String tipo) {
		super(placa, cor, tipo);
		this.tipo = "Moto";
	}
}