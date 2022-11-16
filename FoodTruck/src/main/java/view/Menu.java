/**
 * 
 */
package view;

import java.util.Scanner;

import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVo;

/**
 * @author Gabriel
 *
 */
public class Menu {

	private static final int OPCAO_MENU_VENDA = 1;
	private static final int OPCAO_MENU_PRODUTO = 2;
	private static final int OPCAO_MENU_RELATORIO = 3;
	private static final int OPCAO_MENU_USUARIO = 4;
	private static final int OPCAO_MENU_VOLTAR = 9;

	Scanner scan = new Scanner(System.in);

	public void apresentarMenu(UsuarioVo usuarioVO) {
		int opcao = this.apresentarOpcoesMenu(usuarioVO);
		while (opcao != OPCAO_MENU_VOLTAR) {
			opcao = this.apresentarOpcoesMenu(usuarioVO);
			switch (opcao) {
			case OPCAO_MENU_VENDA:
				MenuVenda menuVenda = new MenuVenda();
				menuVenda.apresentarMenuVenda(usuarioVO);
				break;
			case OPCAO_MENU_PRODUTO:
				if (usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.FUNCIONARIO)
						|| usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ADMINISTRADOR)) {
					System.out.println(OPCAO_MENU_PRODUTO + " - Menu de Produtos");
					System.out.println(OPCAO_MENU_RELATORIO + " - Menu de Relatorios");
				}
				MenuProduto menuProduto = new MenuProduto();
				menuProduto.apresentarMenuProduto();
				break;
			case OPCAO_MENU_RELATORIO:
				if (usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.FUNCIONARIO)
						|| usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ADMINISTRADOR)) {
					System.out.println(OPCAO_MENU_PRODUTO + " - Menu de Produtos");
					System.out.println(OPCAO_MENU_RELATORIO + " - Menu de Relatorios");
				}
				MenuRelatorio menuRelatorio = new MenuRelatorio();
				menuRelatorio.apresentarMenuRelatorio();
				break;
			case OPCAO_MENU_USUARIO:
				if (usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ADMINISTRADOR)) {
					System.out.println(OPCAO_MENU_PRODUTO + " - Menu de Produtos");
					System.out.println(OPCAO_MENU_RELATORIO + " - Menu de Relatorios");
				}
				MenuUsuario menuUsuario = new MenuUsuario();
				menuUsuario.apresentarMenuUsuario();
				break;
			default:
				System.out.println("\nOpção inválida");
				break;
			}
		}
	}

	private int apresentarOpcoesMenu(UsuarioVo usuarioVO) {
		System.out.println("\n---------- Sistema FoodTruck ----------");
		System.out.println("---------- Menu Principal ----------");
		System.out.println("\nOpções: ");
		if (usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.FUNCIONARIO)
				|| usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ADMINISTRADOR)) {
			System.out.println(OPCAO_MENU_PRODUTO + " - Menu de Produtos");
			System.out.println(OPCAO_MENU_RELATORIO + " - Menu de Relatorios");
		}
		if (usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ADMINISTRADOR)) {
			System.out.println(OPCAO_MENU_USUARIO + "Menu de Usuário");
		}
		System.out.println(OPCAO_MENU_VOLTAR + " - Voltar");
		System.out.println("\nDigite uma opção");
		return Integer.parseInt(scan.nextLine());
	}

}
