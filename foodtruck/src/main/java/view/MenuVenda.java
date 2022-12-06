package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.VendaController;
import model.vo.UsuarioVO;
import model.vo.VendaVO;

public class MenuVenda {

	private static final int CADASTRAR_VENDA = 1;
	private static final int CANCELAR_VENDA = 2;
	private static final int VOLTAR = 9;
	
	// submenu flag venda
	static final int SIM = 1;
	static final int NAO = 2;

	public void apresentarMenuVEnda(UsuarioVO usuarioVO) {
		int opcao = 0;
		while (opcao != 9) {
			opcao = this.apresentaOpcoes();
			switch (opcao) {
			case CADASTRAR_VENDA:
				if(this.cadastrarVenda().getIdUsuarioVO() != 0)
					System.out.println("Venda cadastrada com sucesso!");
				else
					System.out.println("Não foi possivel cadastrar venda");
				break;
			case CANCELAR_VENDA:
				if(this.cacelarVenda() < 0)
					System.out.println("Venda cancelada com sucesso!");
				else
					System.out.println("Não foi possivel cancelar venda");
				break;
			default:
				System.out.println("Opção invalida");
				break;
			}
		}
	}

	private int cacelarVenda() {
		return VendaController.cancelarVenda(this.idVenda());
	}

	private int idVenda() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o id da venda que você deseja cancelar");
		return Integer.parseInt(scan.nextLine());
	}

	private VendaVO cadastrarVenda() {
		Scanner scan = new Scanner(System.in);
		VendaVO venda = new VendaVO();
		System.out.println("Digite o id do usuario que fez a compra:");
		venda.setIdUsuarioVO(Integer.parseInt(scan.nextLine()));
		System.out.println("Digite o numero do pedido");
		venda.setNumeroPedido(Integer.parseInt(scan.nextLine()));
		System.out.println("Digite a data da venda no formato yyyy-MM-dd HH:ss:mm");
		venda.setDataVenda(LocalDateTime.parse(scan.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.println("o cliete ja deu flag no pedido?");
		venda.setFlagEntrega(this.flagPedido());
		System.out.println("digite a taxa de entrega");
		venda.setTaxaEntrega(Double.parseDouble(scan.nextLine()));
		return VendaController.cadastrarVenda(venda);
	}

	private boolean flagPedido() {
		Scanner scan = new Scanner(System.in);
		int opcao = 2;
		boolean retorno = false;
		System.out.println(SIM + "- sim");
		System.out.println(NAO + "- nao");
		while(opcao == 2)
		{
			opcao = Integer.parseInt(scan.nextLine());
			switch(opcao)
			{
			case SIM:
				retorno = true;
				break;
			case NAO:
				retorno = false;
				break;
				default:
					System.out.println("opcao invalida");
					break;
			}
		}
		return retorno;
	}

	private int apresentaOpcoes() {
		Scanner scan = new Scanner(System.in);
		System.out.println("opções:");
		System.out.println(CADASTRAR_VENDA + "- cadastrar venda");
		System.out.println(CANCELAR_VENDA + "- cancelar venda");
		System.out.println(VOLTAR + "- voltar");
		return Integer.parseInt(scan.nextLine());
	}

}
