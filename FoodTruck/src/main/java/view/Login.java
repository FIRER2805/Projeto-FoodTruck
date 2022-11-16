package view;

import java.util.Scanner;

import controller.UsuarioController;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVo;

public class Login {
	
	private static final int OPCAO_MENU_LOGIN = 1;
	private static final int OPCAO_MENU_CRIAR_CONTA = 2;
	private static final int OPCAO_MENU_SAIR = 3;
	Scanner scan = new Scanner(System.in);
	
	public void apresentarMenuLogin() 
	{
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_SAIR)
		{
			switch(opcao)
			{
				case OPCAO_MENU_LOGIN: 
				{
					UsuarioVo usuarioVO = this.realizarLogin();
					if(usuarioVO.getIdUsuario() != 0 && usuarioVO.getDataExpiracao() == null)
					{
						System.out.println("\nUsuário Logado: " + usuarioVO.getLogin());
						System.out.println("\nPerfil: " + usuarioVO.getTipoUsuario());
						Menu menu = new Menu();
						menu.apresentarMenu(usuarioVO);
					}
					
					break;
				}
				case OPCAO_MENU_CRIAR_CONTA:
				{
					this.cadastrarNovoUsuario();
					break;
				}
				default:
					System.out.println("\nOpçao inválida");
					break;
				}
			}
		opcao = this.apresentarOpcoesMenu();
		}

	private UsuarioVo realizarLogin() {
		UsuarioVo usuarioVo = new UsuarioVo();
		System.out.println("----------");
		System.out.println("Login: ");
		usuarioVo.setLogin(scan.nextLine());
		System.out.println("Senha: ");
		usuarioVo.setSenha(scan.nextLine());
		
		if(usuarioVo.getLogin().isEmpty() || usuarioVo.getSenha().isEmpty())
		{
			System.out.println("Os campos de login e senha são obrigatórios");
		}
		else
		{
			UsuarioController usuarioController = new UsuarioController();
			usuarioVo = usuarioController.realizarLoginController(usuarioVo);
			if(usuarioVo.getIdUsuario() == 0) 
			{
				System.out.println("usuario não encontrado");
			}
			if(usuarioVo.getDataExpiracao() != null)
			{
				System.out.println("usuario expirado!");
			}
		}
		return usuarioVo;
	}

	private int apresentarOpcoesMenu() {
		System.out.println("---------- Sistema FoodTruck ----------");
		System.out.println("/nOpções: ");
		System.out.println(OPCAO_MENU_LOGIN + " - Entrar");
		System.out.println(OPCAO_MENU_CRIAR_CONTA + " - Criar conta");
		System.out.println(OPCAO_MENU_SAIR + " - Sair");
		System.out.print("\nDigite uma opção: ");
		Scanner teclado = new Scanner(System.in);
		return Integer.parseInt(teclado .nextLine());
	}
	
	private void cadastrarNovoUsuario() {
		UsuarioVo usuarioVo = new UsuarioVo();
		usuarioVo.setTipoUsuario(TipoUsuarioVO.CLIENTE);
		
		MenuUsuario menuUsuario = new MenuUsuario();
		menuUsuario.cadastrarNovoUsuario(usuarioVo);
	}
}
